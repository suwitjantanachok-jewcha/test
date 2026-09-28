package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.QuestionBank
import com.example.data.local.BookmarkEntity
import com.example.data.local.QuizHistoryEntity
import com.example.data.repository.BibleQuizRepository
import com.example.model.BibleQuestion
import com.example.model.DailyVerse
import com.example.model.QuizCategory
import com.example.model.UserAnswer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    QUIZ,
    RESULT,
    REVIEW,
    BOOKMARKS,
    HISTORY,
    SUPPORT
}

data class QuizUiState(
    val currentScreen: AppScreen = AppScreen.HOME,
    val selectedCategory: QuizCategory = QuizCategory.ALL,
    val questions: List<BibleQuestion> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswerIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val userAnswers: List<UserAnswer> = emptyList(),
    val score: Int = 0,
    val currentStreak: Int = 0,
    val maxStreak: Int = 0,
    val isTimedMode: Boolean = false,
    val secondsRemaining: Int = 15,
    val quizDurationSeconds: Int = 0,
    val fiftyFiftyUsed: Boolean = false,
    val hiddenOptionIndices: Set<Int> = emptySet(),
    val hintUsed: Boolean = false,
    val showHintDialog: Boolean = false,
    val dailyVerse: DailyVerse = QuestionBank.dailyVerses.first(),
    val isCurrentQuestionBookmarked: Boolean = false
)

class QuizViewModel(private val repository: BibleQuizRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    val history: StateFlow<List<QuizHistoryEntity>> = repository.historyList
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val bookmarks: StateFlow<List<BookmarkEntity>> = repository.bookmarksList
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private var timerJob: Job? = null
    private var quizStartTimeMs: Long = 0L

    init {
        // Pick a daily verse based on day of year
        val dayIndex = (System.currentTimeMillis() / (1000 * 60 * 60 * 24)).toInt() % QuestionBank.dailyVerses.size
        _uiState.update { it.copy(dailyVerse = QuestionBank.dailyVerses[dayIndex.coerceAtLeast(0)]) }
    }

    fun navigateTo(screen: AppScreen) {
        _uiState.update { it.copy(currentScreen = screen) }
    }

    fun startQuiz(category: QuizCategory) {
        val isTimed = category == QuizCategory.TIMED_CHALLENGE
        val questionList = repository.getQuestions(category, count = 10)
        quizStartTimeMs = System.currentTimeMillis()

        _uiState.update {
            it.copy(
                currentScreen = AppScreen.QUIZ,
                selectedCategory = category,
                questions = questionList,
                currentQuestionIndex = 0,
                selectedAnswerIndex = null,
                isAnswerSubmitted = false,
                userAnswers = emptyList(),
                score = 0,
                currentStreak = 0,
                maxStreak = 0,
                isTimedMode = isTimed,
                secondsRemaining = 15,
                quizDurationSeconds = 0,
                fiftyFiftyUsed = false,
                hiddenOptionIndices = emptySet(),
                hintUsed = false,
                showHintDialog = false,
                isCurrentQuestionBookmarked = false
            )
        }

        checkCurrentQuestionBookmark()
        if (isTimed) {
            startTimer()
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            _uiState.update { it.copy(secondsRemaining = 15) }
            while (_uiState.value.secondsRemaining > 0 && !_uiState.value.isAnswerSubmitted) {
                delay(1000)
                _uiState.update { it.copy(secondsRemaining = it.secondsRemaining - 1) }
            }
            if (!_uiState.value.isAnswerSubmitted) {
                // Time up! Auto-select timeout (-1)
                submitAnswer(-1)
            }
        }
    }

    fun selectAnswer(index: Int) {
        if (_uiState.value.isAnswerSubmitted) return
        submitAnswer(index)
    }

    private fun submitAnswer(index: Int) {
        timerJob?.cancel()
        val currentQ = currentQuestion ?: return
        val isCorrect = index == currentQ.correctIndex

        val newStreak = if (isCorrect) _uiState.value.currentStreak + 1 else 0
        val newMaxStreak = maxOf(_uiState.value.maxStreak, newStreak)
        val newScore = if (isCorrect) _uiState.value.score + 1 else _uiState.value.score

        val answer = UserAnswer(
            question = currentQ,
            selectedIndex = index,
            isCorrect = isCorrect,
            timeSpentSeconds = 15 - _uiState.value.secondsRemaining
        )

        _uiState.update {
            it.copy(
                selectedAnswerIndex = index,
                isAnswerSubmitted = true,
                score = newScore,
                currentStreak = newStreak,
                maxStreak = newMaxStreak,
                userAnswers = it.userAnswers + answer
            )
        }
    }

    fun nextQuestion() {
        val nextIndex = _uiState.value.currentQuestionIndex + 1
        if (nextIndex < _uiState.value.questions.size) {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedAnswerIndex = null,
                    isAnswerSubmitted = false,
                    hiddenOptionIndices = emptySet(),
                    showHintDialog = false,
                    isCurrentQuestionBookmarked = false,
                    secondsRemaining = 15
                )
            }
            checkCurrentQuestionBookmark()
            if (_uiState.value.isTimedMode) {
                startTimer()
            }
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        timerJob?.cancel()
        val duration = ((System.currentTimeMillis() - quizStartTimeMs) / 1000).toInt()
        val total = _uiState.value.questions.size
        val score = _uiState.value.score

        viewModelScope.launch {
            repository.saveQuizResult(
                categoryName = _uiState.value.selectedCategory.title,
                score = score,
                totalQuestions = total,
                durationSeconds = duration
            )
        }

        _uiState.update {
            it.copy(
                currentScreen = AppScreen.RESULT,
                quizDurationSeconds = duration
            )
        }
    }

    fun useFiftyFifty() {
        if (_uiState.value.fiftyFiftyUsed || _uiState.value.isAnswerSubmitted) return
        val currentQ = currentQuestion ?: return

        val incorrectIndices = currentQ.options.indices.filter { it != currentQ.correctIndex }
        val toHide = incorrectIndices.shuffled().take(2).toSet()

        _uiState.update {
            it.copy(
                fiftyFiftyUsed = true,
                hiddenOptionIndices = toHide
            )
        }
    }

    fun toggleHintDialog(show: Boolean) {
        _uiState.update {
            it.copy(
                showHintDialog = show,
                hintUsed = if (show) true else it.hintUsed
            )
        }
    }

    fun toggleBookmarkCurrentQuestion() {
        val currentQ = currentQuestion ?: return
        val currentlyBookmarked = _uiState.value.isCurrentQuestionBookmarked
        viewModelScope.launch {
            repository.toggleBookmark(currentQ, currentlyBookmarked)
            _uiState.update { it.copy(isCurrentQuestionBookmarked = !currentlyBookmarked) }
        }
    }

    fun toggleBookmarkForQuestion(question: BibleQuestion, isBookmarked: Boolean) {
        viewModelScope.launch {
            repository.toggleBookmark(question, isBookmarked)
            if (currentQuestion?.id == question.id) {
                _uiState.update { it.copy(isCurrentQuestionBookmarked = !isBookmarked) }
            }
        }
    }

    fun deleteBookmark(bookmarkId: Int) {
        viewModelScope.launch {
            repository.removeBookmarkById(bookmarkId)
        }
    }

    fun clearAllHistory() {
        viewModelScope.launch {
            repository.clearHistory()
        }
    }

    private fun checkCurrentQuestionBookmark() {
        val currentQ = currentQuestion ?: return
        viewModelScope.launch {
            repository.isBookmarked(currentQ.id).collect { isBookmarked ->
                _uiState.update { it.copy(isCurrentQuestionBookmarked = isBookmarked) }
            }
        }
    }

    val currentQuestion: BibleQuestion?
        get() {
            val index = _uiState.value.currentQuestionIndex
            val questions = _uiState.value.questions
            return if (index in questions.indices) questions[index] else null
        }
}
