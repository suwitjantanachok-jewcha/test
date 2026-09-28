package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BibleCorrectGreen
import com.example.ui.theme.BibleGoldSecondary
import com.example.ui.theme.BibleIncorrectRed
import com.example.ui.viewmodel.QuizUiState

@Composable
fun ResultScreen(
    uiState: QuizUiState,
    onPlayAgain: () -> Unit,
    onReviewAnswers: () -> Unit,
    onReturnHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Intercept back button to return to home
    BackHandler {
        onReturnHome()
    }

    val total = uiState.questions.size
    val correct = uiState.score
    val wrong = total - correct
    val percentage = if (total > 0) (correct * 100) / total else 0

    val (badgeEmoji, titleText, encouragementText) = when {
        percentage >= 90 -> Triple(
            "👑",
            "ผู้เชี่ยวชาญพระวจนะ!",
            "\"พระวจนะของพระองค์เป็นโคมสำหรับเท้าของข้าพระองค์ และเป็นความสว่างแก่มรรคาของข้าพระองค์\" (สดุดี 119:105)"
        )
        percentage >= 70 -> Triple(
            "🌟",
            "ยอดเยี่ยมมาก!",
            "\"จงเติบโตขึ้นในพระคุณและในความรู้ถึงพระเยซูคริสต์องค์พระผู้เป็นเจ้า\" (2 เปโตร 3:18)"
        )
        percentage >= 50 -> Triple(
            "📖",
            "ทำได้ดีมาก!",
            "\"พระคัมภีร์ทุกตอนได้รับการดลใจจากพระเจ้า และเป็นประโยชน์ในการสอน\" (2 ทิโมธี 3:16)"
        )
        else -> Triple(
            "🌱",
            "เริ่มต้นการเรียนรู้ที่ดี!",
            "\"จงขอแล้วจะได้ จงหาแล้วจะพบ จงเคาะแล้วจะเปิดให้แก่ท่าน\" (มัทธิว 7:7)"
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .testTag("result_screen"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Trophy / Badge Icon
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(BibleGoldSecondary.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badgeEmoji,
                    fontSize = 50.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = titleText,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Text(
                text = "หมวด: ${uiState.selectedCategory.title}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Score Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("score_summary_card"),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$percentage%",
                        fontSize = 46.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "ตอบถูก $correct จาก $total ข้อ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Metrics Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MetricItem(
                            icon = Icons.Default.CheckCircle,
                            iconColor = BibleCorrectGreen,
                            label = "ถูก",
                            value = "$correct ข้อ"
                        )
                        MetricItem(
                            icon = Icons.Default.Close,
                            iconColor = BibleIncorrectRed,
                            label = "ผิด",
                            value = "$wrong ข้อ"
                        )
                        MetricItem(
                            icon = Icons.Default.Whatshot,
                            iconColor = BibleGoldSecondary,
                            label = "สตรีคสูงสุด",
                            value = "x${uiState.maxStreak}"
                        )
                        MetricItem(
                            icon = Icons.Default.Timer,
                            iconColor = MaterialTheme.colorScheme.primary,
                            label = "เวลา",
                            value = "${uiState.quizDurationSeconds}s"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Biblical Encouragement Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                )
            ) {
                Text(
                    text = encouragementText,
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = onReviewAnswers,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("review_answers_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Default.MenuBook, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("ทบทวนคำตอบและข้อพระคัมภีร์", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
                onClick = onPlayAgain,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("play_again_button"),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Replay, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("เล่นอีกครั้ง", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }

            TextButton(
                onClick = onReturnHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("return_home_button")
            ) {
                Icon(Icons.Default.Home, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text("กลับหน้าหลัก", fontSize = 15.sp)
            }
        }
    }
}

@Composable
private fun MetricItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    label: String,
    value: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
