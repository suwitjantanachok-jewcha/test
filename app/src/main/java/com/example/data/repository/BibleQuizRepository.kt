package com.example.data.repository

import com.example.data.QuestionBank
import com.example.data.local.BibleQuizDao
import com.example.data.local.BookmarkEntity
import com.example.data.local.QuizHistoryEntity
import com.example.model.BibleQuestion
import com.example.model.QuizCategory
import kotlinx.coroutines.flow.Flow

class BibleQuizRepository(private val dao: BibleQuizDao) {

    val historyList: Flow<List<QuizHistoryEntity>> = dao.getAllHistory()
    val bookmarksList: Flow<List<BookmarkEntity>> = dao.getAllBookmarks()

    fun isBookmarked(questionId: Int): Flow<Boolean> = dao.isBookmarked(questionId)

    suspend fun saveQuizResult(
        categoryName: String,
        score: Int,
        totalQuestions: Int,
        durationSeconds: Int
    ) {
        val percentage = if (totalQuestions > 0) (score * 100) / totalQuestions else 0
        dao.insertHistory(
            QuizHistoryEntity(
                categoryName = categoryName,
                score = score,
                totalQuestions = totalQuestions,
                percentage = percentage,
                durationSeconds = durationSeconds
            )
        )
    }

    suspend fun clearHistory() {
        dao.clearAllHistory()
    }

    suspend fun toggleBookmark(question: BibleQuestion, currentlyBookmarked: Boolean) {
        if (currentlyBookmarked) {
            dao.deleteBookmarkByQuestionId(question.id)
        } else {
            dao.insertBookmark(
                BookmarkEntity(
                    questionId = question.id,
                    questionText = question.question,
                    scriptureRef = question.scriptureReference,
                    explanation = question.explanation
                )
            )
        }
    }

    suspend fun removeBookmarkById(id: Int) {
        dao.deleteBookmarkById(id)
    }

    fun getQuestions(category: QuizCategory, count: Int = 10): List<BibleQuestion> {
        return QuestionBank.getQuestionsForCategory(category, count)
    }
}
