package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuestionBank
import com.example.ui.theme.BibleGoldSecondary

private const val YOUTUBE_CHANNEL_URL = "https://www.youtube.com/@jewcha"
private const val YOUTUBE_CHANNEL_NAME = "jewcha"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportCreatorScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    BackHandler {
        onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "สนับสนุนผู้สร้าง",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("support_back_button")
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
        modifier = modifier.testTag("support_screen")
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // YouTube Channel Banner Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("youtube_creator_card"),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // YouTube Logo / Avatar Circle
                        Box(
                            modifier = Modifier
                                .size(88.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(Color(0xFFFF0000), Color(0xFFCC0000))
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = "YouTube",
                                tint = Color.White,
                                modifier = Modifier.size(54.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Surface(
                            color = Color(0xFFFF0000).copy(alpha = 0.12f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "🔴 YouTube Channel",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFCC0000)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = YOUTUBE_CHANNEL_NAME,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "@$YOUTUBE_CHANNEL_NAME",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "ขอเชิญชวนพี่น้องช่วยกันกดติดตาม (Subscribe) ช่อง YouTube \"$YOUTUBE_CHANNEL_NAME\" เพื่อร่วมสนับสนุนและเป็นกำลังใจให้ผู้สร้างในการผลิตสื่อคริสเตียน คอนเทนต์สร้างสรรค์ และพัฒนาแอพเพื่อพระวจนะต่อไปครับ ❤️",
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Big Subscribe Button
                        Button(
                            onClick = { openYouTubeChannel(context) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("subscribe_youtube_button"),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC0000))
                        ) {
                            Icon(
                                Icons.Default.Subscriptions,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "กดติดตามช่อง YouTube jewcha",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(YOUTUBE_CHANNEL_URL))
                                    Toast.makeText(context, "คัดลอกลิงก์ช่อง jewcha แล้ว", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("copy_channel_link_button"),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("คัดลอกลิงก์", fontSize = 13.sp)
                            }

                            OutlinedButton(
                                onClick = {
                                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            "ช่วยกันกดติดตาม (Subscribe) ช่อง YouTube ของ jewcha เพื่อสนับสนุนการพัฒนาสื่อและแอพแบบทดสอบพระคัมภีร์ไบเบิล: $YOUTUBE_CHANNEL_URL"
                                        )
                                    }
                                    context.startActivity(Intent.createChooser(shareIntent, "แชร์ช่อง YouTube jewcha"))
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("share_channel_button"),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("แชร์บอกต่อ", fontSize = 13.sp)
                            }
                        }
                    }
                }
            }

            // Ways to Support List
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                    )
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "วิธีร่วมสนับสนุนง่ายๆ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        SupportTipItem(
                            icon = Icons.Default.Subscriptions,
                            title = "1. กด Subscribe ช่อง jewcha",
                            desc = "กดติดตามช่องบน YouTube เป็นกำลังใจที่สำคัญที่สุดให้แก่ผู้สร้าง"
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        SupportTipItem(
                            icon = Icons.Default.NotificationsActive,
                            title = "2. กดกระดิ่งแจ้งเตือน",
                            desc = "รับการแจ้งเตือนทันทีเมื่อมีคลิปใหม่ อัพเดท และเนื้อหาใหม่ๆ"
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        SupportTipItem(
                            icon = Icons.Default.Share,
                            title = "3. ส่งต่อแอพ & ช่อง YouTube ให้เพื่อน",
                            desc = "แบ่งปันให้พี่น้องในคริสตจักร กลุ่มเซลล์ หรือครอบครัวได้ร่วมสนุกและเรียนรู้พระวจนะ"
                        )
                    }
                }
            }

            // Bible Version Attribution (THSV11)
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("bible_version_card"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                    )
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = QuestionBank.BIBLE_VERSION_TITLE,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "แอพพลิเคชันนี้ใช้เนื้อหา คำถาม ข้ออ้างอิง และข้อพระคัมภีร์ทั้งหมดตาม \"พระคริสตธรรมคัมภีร์ ฉบับมาตรฐาน 2011 (THSV11)\" ลิขสิทธิ์โดยสมาคมพระคริสตธรรมไทย (Thailand Bible Society - TBS)",
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "จัดทำขึ้นเพื่อการศึกษา เรียนรู้พระวจนะ และเสริมสร้างจิตวิญญาณของผู้เชื่อ",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SupportTipItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    desc: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(BibleGoldSecondary.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = BibleGoldSecondary,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = desc,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

fun openYouTubeChannel(context: Context) {
    try {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://www.youtube.com/@$YOUTUBE_CHANNEL_NAME"))
        appIntent.setPackage("com.google.android.youtube")
        context.startActivity(appIntent)
    } catch (_: Exception) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_CHANNEL_URL))
        context.startActivity(webIntent)
    }
}
