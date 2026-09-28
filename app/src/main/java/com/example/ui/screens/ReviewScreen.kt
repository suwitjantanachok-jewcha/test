package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BibleQuestion
import com.example.model.UserAnswer
import com.example.ui.theme.BibleCorrectGreen
import com.example.ui.theme.BibleGoldSecondary
import com.example.ui.theme.BibleIncorrectRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    userAnswers: List<UserAnswer>,
    bookmarkedQuestionIds: Set<Int>,
    onToggleBookmark: (BibleQuestion, Boolean) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ทบทวนคำตอบ (${userAnswers.size} ข้อ)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("review_back_button")
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "กลับ"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier.testTag("review_screen")
    ) { innerPadding ->
        if (userAnswers.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("ไม่มีข้อมูลคำตอบให้ทบทวน")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                itemsIndexed(userAnswers) { index, answer ->
                    val q = answer.question
                    val isBookmarked = bookmarkedQuestionIds.contains(q.id)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("review_item_$index"),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Header: Question index, Status, Bookmark
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (answer.isCorrect) BibleCorrectGreen.copy(alpha = 0.15f)
                                                else BibleIncorrectRed.copy(alpha = 0.15f)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = if (answer.isCorrect) Icons.Default.CheckCircle else Icons.Default.Close,
                                            contentDescription = null,
                                            tint = if (answer.isCorrect) BibleCorrectGreen else BibleIncorrectRed,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "ข้อที่ ${index + 1}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = if (answer.isCorrect) BibleCorrectGreen else BibleIncorrectRed
                                    )
                                }

                                IconButton(
                                    onClick = { onToggleBookmark(q, isBookmarked) },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                        contentDescription = "บันทึก",
                                        tint = if (isBookmarked) BibleGoldSecondary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = q.question,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // User Answer
                            val selectedText = if (answer.selectedIndex in q.options.indices) {
                                q.options[answer.selectedIndex]
                            } else {
                                "หมดเวลา (ไม่ได้ตอบ)"
                            }

                            Row(modifier = Modifier.padding(vertical = 2.dp)) {
                                Text(
                                    text = "คำตอบของคุณ: ",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = selectedText,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (answer.isCorrect) BibleCorrectGreen else BibleIncorrectRed
                                )
                            }

                            // Correct Answer if user was wrong
                            if (!answer.isCorrect) {
                                Row(modifier = Modifier.padding(vertical = 2.dp)) {
                                    Text(
                                        text = "คำตอบที่ถูก: ",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = q.options[q.correctIndex],
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BibleCorrectGreen
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Explanation box
                            Surface(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = q.explanation,
                                        fontSize = 13.sp,
                                        lineHeight = 18.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "📖 ${q.scriptureReference}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BibleGoldSecondary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
