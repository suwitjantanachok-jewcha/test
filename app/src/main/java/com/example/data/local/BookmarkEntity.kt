package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val questionId: Int,
    val questionText: String,
    val scriptureRef: String,
    val explanation: String,
    val bookmarkedAt: Long = System.currentTimeMillis()
)
