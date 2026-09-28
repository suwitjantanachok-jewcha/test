package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [QuizHistoryEntity::class, BookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BibleQuizDatabase : RoomDatabase() {

    abstract fun bibleQuizDao(): BibleQuizDao

    companion object {
        @Volatile
        private var INSTANCE: BibleQuizDatabase? = null

        fun getDatabase(context: Context): BibleQuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BibleQuizDatabase::class.java,
                    "bible_quiz_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
