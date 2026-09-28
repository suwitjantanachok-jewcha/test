package com.example.model

data class BibleQuestion(
    val id: Int,
    val category: QuizCategory,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val scriptureReference: String,
    val difficulty: QuestionDifficulty = QuestionDifficulty.EASY
)

data class UserAnswer(
    val question: BibleQuestion,
    val selectedIndex: Int,
    val isCorrect: Boolean,
    val timeSpentSeconds: Int
)

data class DailyVerse(
    val verseText: String,
    val reference: String,
    val theme: String
)
