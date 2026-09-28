package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_history")
data class QuizHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryName: String,
    val score: Int,
    val totalQuestions: Int,
    val percentage: Int,
    val completedAt: Long = System.currentTimeMillis(),
    val durationSeconds: Int = 0
)
