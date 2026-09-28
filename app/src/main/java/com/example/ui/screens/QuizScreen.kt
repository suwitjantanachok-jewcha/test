package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BibleQuestion
import com.example.ui.theme.BibleCorrectGreen
import com.example.ui.theme.BibleCorrectGreenContainer
import com.example.ui.theme.BibleGoldSecondary
import com.example.ui.theme.BibleIncorrectRed
import com.example.ui.theme.BibleIncorrectRedContainer
import com.example.ui.viewmodel.QuizUiState

@Composable
fun QuizScreen(
    uiState: QuizUiState,
    currentQuestion: BibleQuestion?,
    onSelectAnswer: (Int) -> Unit,
    onNextQuestion: () -> Unit,
    onFiftyFifty: () -> Unit,
    onToggleHint: (Boolean) -> Unit,
    onToggleBookmark: () -> Unit,
    onExitQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showExitDialog by remember { mutableStateOf(false) }

    // Intercept back button to warn user
    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("ออกจากแบบทดสอบ?") },
            text = { Text("หากออกตอนนี้ ความคืบหน้าของรอบนี้จะไม่ถูกบันทึก คุณแน่ใจหรือไม่?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showExitDialog = false
                        onExitQuiz()
                    },
                    modifier = Modifier.testTag("confirm_exit_button")
                ) {
                    Text("ออกจากแบบทดสอบ", color = BibleIncorrectRed)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showExitDialog = false },
                    modifier = Modifier.testTag("cancel_exit_button")
                ) {
                    Text("ทำต่อ")
                }
            }
        )
    }

    if (uiState.showHintDialog && currentQuestion != null) {
        AlertDialog(
            onDismissRequest = { onToggleHint(false) },
            icon = {
                Icon(
                    Icons.Default.Lightbulb,
                    contentDescription = null,
                    tint = BibleGoldSecondary,
                    modifier = Modifier.size(32.dp)
                )
            },
            title = { Text("คำใบ้ข้อพระคัมภีร์") },
            text = {
                Column {
                    Text("ข้อนี้มีบันทึกอยู่ในพระธรรม:")
                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = currentQuestion.scriptureReference,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(12.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { onToggleHint(false) },
                    modifier = Modifier.testTag("close_hint_button")
                ) {
                    Text("เข้าใจแล้ว")
                }
            }
        )
    }

    if (currentQuestion == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("ไม่พบข้อคำถาม")
        }
        return
    }

    val total = uiState.questions.size
    val currentNumber = uiState.currentQuestionIndex + 1
    val progress = currentNumber.toFloat() / total.coerceAtLeast(1)
    val isLast = currentNumber >= total
    val letters = listOf("ก", "ข", "ค", "ง")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("quiz_screen")
    ) {
        // Top App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { showExitDialog = true },
                modifier = Modifier.testTag("quiz_back_button")
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "กลับ"
                )
            }

            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "${uiState.selectedCategory.iconEmoji} ${uiState.selectedCategory.title}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            IconButton(
                onClick = onToggleBookmark,
                modifier = Modifier.testTag("quiz_bookmark_button")
            ) {
                Icon(
                    imageVector = if (uiState.isCurrentQuestionBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = "บันทึกข้อนี้",
                    tint = if (uiState.isCurrentQuestionBookmarked) BibleGoldSecondary else MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Progress bar
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )

        // Status Row (Question Count, Streak, Score, Timer)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ข้อ $currentNumber จาก $total",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (uiState.currentStreak > 1) {
                    Surface(
                        color = BibleGoldSecondary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "🔥 สตรีค x${uiState.currentStreak}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = BibleGoldSecondary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Surface(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "คะแนน: ${uiState.score}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                if (uiState.isTimedMode) {
                    val timerColor = if (uiState.secondsRemaining <= 5) BibleIncorrectRed else MaterialTheme.colorScheme.primary
                    Surface(
                        color = timerColor.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Timer,
                                contentDescription = "เวลา",
                                tint = timerColor,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${uiState.secondsRemaining}s",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = timerColor
                            )
                        }
                    }
                }
            }
        }

        // Scrollable Content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            // Question Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .testTag("question_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = currentQuestion.question,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 26.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Lifelines Row (50:50 and Bible Verse Hint)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onFiftyFifty,
                    enabled = !uiState.fiftyFiftyUsed && !uiState.isAnswerSubmitted,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("lifeline_fifty_fifty"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("50:50 ${if (uiState.fiftyFiftyUsed) "✓" else ""}")
                }

                OutlinedButton(
                    onClick = { onToggleHint(true) },
                    enabled = !uiState.isAnswerSubmitted,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("lifeline_hint"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        Icons.Default.HelpOutline,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("คำใบ้ข้อคัมภีร์")
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // 4 Options
            currentQuestion.options.forEachIndexed { index, optionText ->
                val isHidden = uiState.hiddenOptionIndices.contains(index)
                val isSelected = uiState.selectedAnswerIndex == index
                val isCorrect = index == currentQuestion.correctIndex

                val (containerColor, borderColor, textColor) = when {
                    !uiState.isAnswerSubmitted -> {
                        if (isSelected) {
                            Triple(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimaryContainer)
                        } else {
                            Triple(MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.outlineVariant, MaterialTheme.colorScheme.onSurface)
                        }
                    }
                    isCorrect -> {
                        Triple(BibleCorrectGreenContainer, BibleCorrectGreen, Color(0xFF065F46))
                    }
                    isSelected && !isCorrect -> {
                        Triple(BibleIncorrectRedContainer, BibleIncorrectRed, Color(0xFF991B1B))
                    }
                    else -> {
                        Triple(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f), Color.Transparent, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                    }
                }

                if (!isHidden) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable(enabled = !uiState.isAnswerSubmitted) {
                                onSelectAnswer(index)
                            }
                            .testTag("quiz_option_$index"),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = containerColor),
                        border = BorderStroke(1.5.dp, borderColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (uiState.isAnswerSubmitted && isCorrect) BibleCorrectGreen
                                        else if (uiState.isAnswerSubmitted && isSelected && !isCorrect) BibleIncorrectRed
                                        else MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (uiState.isAnswerSubmitted && isCorrect) {
                                    Icon(
                                        Icons.Default.CheckCircle,
                                        contentDescription = "ถูก",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else if (uiState.isAnswerSubmitted && isSelected && !isCorrect) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "ผิด",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else {
                                    Text(
                                        text = letters.getOrElse(index) { "${index + 1}" },
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = optionText,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = textColor,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Explanation Card after answering
            AnimatedVisibility(
                visible = uiState.isAnswerSubmitted,
                enter = fadeIn() + slideInVertically()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .testTag("explanation_card"),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (uiState.selectedAnswerIndex == currentQuestion.correctIndex)
                            BibleCorrectGreenContainer.copy(alpha = 0.4f)
                        else
                            BibleIncorrectRedContainer.copy(alpha = 0.4f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (uiState.selectedAnswerIndex == currentQuestion.correctIndex)
                                    Icons.Default.CheckCircle
                                else
                                    Icons.Default.Close,
                                contentDescription = null,
                                tint = if (uiState.selectedAnswerIndex == currentQuestion.correctIndex)
                                    BibleCorrectGreen
                                else
                                    BibleIncorrectRed,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (uiState.selectedAnswerIndex == currentQuestion.correctIndex)
                                    "ถูกต้อง! ยอดเยี่ยมมาก 🎉"
                                else
                                    "ยังไม่ถูกต้อง",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = if (uiState.selectedAnswerIndex == currentQuestion.correctIndex)
                                    BibleCorrectGreen
                                else
                                    BibleIncorrectRed
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = currentQuestion.explanation,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Surface(
                            color = BibleGoldSecondary.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "📖 อ้างอิง: ${currentQuestion.scriptureReference}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = BibleGoldSecondary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Bottom Action Button
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = onNextQuestion,
                    enabled = uiState.isAnswerSubmitted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("quiz_next_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = if (isLast) "ดูผลคะแนน 🏆" else "ข้อต่อไป",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = if (isLast) Icons.Default.EmojiEvents else Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
