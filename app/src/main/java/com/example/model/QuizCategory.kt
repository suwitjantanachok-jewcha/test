package com.example.model

enum class QuizCategory(
    val title: String,
    val description: String,
    val iconEmoji: String
) {
    ALL(
        title = "รวมทุกหมวด",
        description = "สุ่มข้อสอบจากทุกตอน ทั้งเก่าและใหม่",
        iconEmoji = "🌟"
    ),
    OLD_TESTAMENT(
        title = "พันธสัญญาเดิม",
        description = "การสร้างโลก โนอาห์ โมเสส บรรพชน และผู้เผยพระวจนะ",
        iconEmoji = "📜"
    ),
    NEW_TESTAMENT(
        title = "พันธสัญญาใหม่",
        description = "พระเยซูคริสต์ อัครทูต การอัศจรรย์ และคริสตจักรแรก",
        iconEmoji = "✝️"
    ),
    PEOPLE(
        title = "บุคคลสำคัญ",
        description = "เรื่องราวชีวิตและความเชื่อของบุคคลในพระคัมภีร์",
        iconEmoji = "👥"
    ),
    VERSES_TRIVIA(
        title = "ข้อพระคัมภีร์ & เกร็ดน่ารู้",
        description = "ข้อพระคัมภีร์จำยอดนิยมและคำอุปมาสำคัญ",
        iconEmoji = "📖"
    ),
    TIMED_CHALLENGE(
        title = "ท้าทายจับเวลา",
        description = "ทดสอบความไว 15 วินาทีต่อข้อ ชิงคะแนนสูงสุด!",
        iconEmoji = "⚡"
    )
}

enum class QuestionDifficulty {
    EASY, MEDIUM, HARD
}
