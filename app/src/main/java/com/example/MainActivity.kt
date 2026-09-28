package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.local.BibleQuizDatabase
import com.example.data.repository.BibleQuizRepository
import com.example.model.BibleQuestion
import com.example.model.QuizCategory
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.HistoryScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ResultScreen
import com.example.ui.screens.ReviewScreen
import com.example.ui.screens.SupportCreatorScreen
import com.example.ui.theme.BibleQuizTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = BibleQuizDatabase.getDatabase(applicationContext)
        val repository = BibleQuizRepository(database.bibleQuizDao())
        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return QuizViewModel(repository) as T
            }
        }

        setContent {
            BibleQuizTheme {
                val viewModel: QuizViewModel = viewModel(factory = viewModelFactory)
                BibleQuizApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun BibleQuizApp(viewModel: QuizViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val historyList by viewModel.history.collectAsStateWithLifecycle()
    val bookmarksList by viewModel.bookmarks.collectAsStateWithLifecycle()

    val showBottomBar = uiState.currentScreen in listOf(
        AppScreen.HOME,
        AppScreen.BOOKMARKS,
        AppScreen.HISTORY,
        AppScreen.SUPPORT
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    modifier = Modifier.testTag("main_bottom_nav"),
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    NavigationBarItem(
                        selected = uiState.currentScreen == AppScreen.HOME,
                        onClick = { viewModel.navigateTo(AppScreen.HOME) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.currentScreen == AppScreen.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                                contentDescription = "หน้าหลัก"
                            )
                        },
                        label = { Text("หน้าหลัก") },
                        modifier = Modifier.testTag("nav_home")
                    )

                    NavigationBarItem(
                        selected = uiState.currentScreen == AppScreen.BOOKMARKS,
                        onClick = { viewModel.navigateTo(AppScreen.BOOKMARKS) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.currentScreen == AppScreen.BOOKMARKS) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                contentDescription = "ข้อบันทึก"
                            )
                        },
                        label = { Text("ข้อบันทึก") },
                        modifier = Modifier.testTag("nav_bookmarks")
                    )

                    NavigationBarItem(
                        selected = uiState.currentScreen == AppScreen.HISTORY,
                        onClick = { viewModel.navigateTo(AppScreen.HISTORY) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.currentScreen == AppScreen.HISTORY) Icons.Filled.History else Icons.Outlined.History,
                                contentDescription = "ประวัติ"
                            )
                        },
                        label = { Text("ประวัติ") },
                        modifier = Modifier.testTag("nav_history")
                    )

                    NavigationBarItem(
                        selected = uiState.currentScreen == AppScreen.SUPPORT,
                        onClick = { viewModel.navigateTo(AppScreen.SUPPORT) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.currentScreen == AppScreen.SUPPORT) Icons.Filled.Subscriptions else Icons.Outlined.Subscriptions,
                                contentDescription = "ผู้สร้าง",
                                tint = if (uiState.currentScreen == AppScreen.SUPPORT) Color(0xFFCC0000) else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        label = { Text("ผู้สร้าง") },
                        modifier = Modifier.testTag("nav_support")
                    )
                }
            }
        }
    ) { innerPadding ->
        AnimatedContent(
            targetState = uiState.currentScreen,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "screen_transition",
            modifier = Modifier.padding(innerPadding)
        ) { screen ->
            when (screen) {
                AppScreen.HOME -> {
                    HomeScreen(
                        dailyVerse = uiState.dailyVerse,
                        historyList = historyList,
                        bookmarksList = bookmarksList,
                        onSelectCategory = { category ->
                            viewModel.startQuiz(category)
                        },
                        onNavigate = { targetScreen ->
                            viewModel.navigateTo(targetScreen)
                        }
                    )
                }

                AppScreen.QUIZ -> {
                    QuizScreen(
                        uiState = uiState,
                        currentQuestion = viewModel.currentQuestion,
                        onSelectAnswer = { index ->
                            viewModel.selectAnswer(index)
                        },
                        onNextQuestion = {
                            viewModel.nextQuestion()
                        },
                        onFiftyFifty = {
                            viewModel.useFiftyFifty()
                        },
                        onToggleHint = { show ->
                            viewModel.toggleHintDialog(show)
                        },
                        onToggleBookmark = {
                            viewModel.toggleBookmarkCurrentQuestion()
                        },
                        onExitQuiz = {
                            viewModel.navigateTo(AppScreen.HOME)
                        }
                    )
                }

                AppScreen.RESULT -> {
                    ResultScreen(
                        uiState = uiState,
                        onPlayAgain = {
                            viewModel.startQuiz(uiState.selectedCategory)
                        },
                        onReviewAnswers = {
                            viewModel.navigateTo(AppScreen.REVIEW)
                        },
                        onReturnHome = {
                            viewModel.navigateTo(AppScreen.HOME)
                        }
                    )
                }

                AppScreen.REVIEW -> {
                    val bookmarkedIds = bookmarksList.map { it.questionId }.toSet()
                    ReviewScreen(
                        userAnswers = uiState.userAnswers,
                        bookmarkedQuestionIds = bookmarkedIds,
                        onToggleBookmark = { question, isBookmarked ->
                            viewModel.toggleBookmarkForQuestion(question, isBookmarked)
                        },
                        onBack = {
                            viewModel.navigateTo(AppScreen.RESULT)
                        }
                    )
                }

                AppScreen.BOOKMARKS -> {
                    BookmarksScreen(
                        bookmarks = bookmarksList,
                        onDeleteBookmark = { bookmarkId ->
                            viewModel.deleteBookmark(bookmarkId)
                        },
                        onBack = {
                            viewModel.navigateTo(AppScreen.HOME)
                        }
                    )
                }

                AppScreen.HISTORY -> {
                    HistoryScreen(
                        historyList = historyList,
                        onClearHistory = {
                            viewModel.clearAllHistory()
                        },
                        onBack = {
                            viewModel.navigateTo(AppScreen.HOME)
                        }
                    )
                }

                AppScreen.SUPPORT -> {
                    SupportCreatorScreen(
                        onBack = {
                            viewModel.navigateTo(AppScreen.HOME)
                        }
                    )
                }
            }
        }
    }
}
