package com.example.data

import com.example.model.BibleQuestion
import com.example.model.DailyVerse
import com.example.model.QuestionDifficulty
import com.example.model.QuizCategory

object QuestionBank {

    const val BIBLE_VERSION_TITLE = "พระคริสตธรรมคัมภีร์ ฉบับมาตรฐาน (THSV11)"
    const val BIBLE_VERSION_DESCRIPTION = "ข้อพระคัมภีร์ คำสอน และการอ้างอิงทั้งหมดในแอพนี้อ้างอิงตามพระคริสตธรรมคัมภีร์ ฉบับมาตรฐาน 2011 (THSV11)"

    val dailyVerses = listOf(
        DailyVerse(
            verseText = "เพราะว่าพระเจ้าทรงรักโลก จนได้ทรงประทานพระบุตรองค์เดียวของพระองค์ เพื่อทุกคนที่วางใจในพระบุตรนั้นจะไม่พินาศ แต่มีชีวิตนิรันดร์",
            reference = "ยอห์น 3:16 (THSV11)",
            theme = "ความรักแห่งความรอด"
        ),
        DailyVerse(
            verseText = "พระยาห์เวห์ทรงเป็นผู้เลี้ยงดูข้าพเจ้า ข้าพเจ้าจะไม่ขัดสน",
            reference = "สดุดี 23:1 (THSV11)",
            theme = "การทรงเลี้ยงดู"
        ),
        DailyVerse(
            verseText = "ข้าพเจ้าผจญทุกสิ่งได้ โดยพระองค์ผู้ทรงเสริมกำลังข้าพเจ้า",
            reference = "ฟิลิปปี 4:13 (THSV11)",
            theme = "กำลังจากพระเจ้า"
        ),
        DailyVerse(
            verseText = "จงวางใจในพระยาห์เวห์ด้วยสุดใจของเจ้า และอย่าพึ่งพาความรอบรู้ของตนเอง",
            reference = "สุภาษิต 3:5 (THSV11)",
            theme = "ความวางใจ"
        ),
        DailyVerse(
            verseText = "พระวจนะของพระองค์เป็นโคมสำหรับเท้าของข้าพระองค์ และเป็นความสว่างแก่มรรคาของข้าพระองค์",
            reference = "สดุดี 119:105 (THSV11)",
            theme = "ความสว่างนำทาง"
        ),
        DailyVerse(
            verseText = "แต่ผลของพระวิญญาณนั้นคือ ความรัก ความยินดี สันติสุข ความอดทน ความกรุณา ความดี ความสัตย์ซื่อ ความสุภาพอ่อนโยน การรู้จักบังคับตน",
            reference = "กาลาเทีย 5:22-23 (THSV11)",
            theme = "ผลของพระวิญญาณ"
        )
    )

    val questions: List<BibleQuestion> = listOf(
        // === พันธสัญญาเดิม (OLD_TESTAMENT - THSV11) ===
        BibleQuestion(
            id = 1,
            category = QuizCategory.OLD_TESTAMENT,
            question = "พระเจ้าทรงสร้างฟ้าสวรรค์และแผ่นดินโลก และทรงหยุดพักจากการงานในวันที่เท่าใด?",
            options = listOf("วันที่ 5", "วันที่ 6", "วันที่ 7", "วันที่ 8"),
            correctIndex = 2,
            explanation = "พระเจ้าทรงสร้างทุกสิ่งเสร็จสิ้นใน 6 วัน และในวันที่เจ็ดพระองค์ทรงหยุดพักจากการงานทั้งสิ้นที่ทรงกระทำ",
            scriptureReference = "ปฐมกาล 2:2-3 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 2,
            category = QuizCategory.OLD_TESTAMENT,
            question = "ใครเป็นผู้ต่อเรือขนาดใหญ่ด้วยไม้สนโกเฟอร์ เพื่อรอดพ้นจากน้ำท่วมโลกตามพระบัญชาของพระเจ้า?",
            options = listOf("อับราฮัม", "โนอาห์", "โมเสส", "โยบ"),
            correctIndex = 1,
            explanation = "พระเจ้าทรงบัญชาให้โนอาห์ต่อเรือด้วยไม้สนโกเฟอร์เพื่อช่วยชีวิตครอบครัวและสิ่งมีชีวิตทุกชนิดให้อยู่รอด",
            scriptureReference = "ปฐมกาล 6:13-14 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 3,
            category = QuizCategory.OLD_TESTAMENT,
            question = "ทะเลใดที่พระเจ้าทรงแยกออกเพื่อให้โมเสสพาคนอิสราเอลข้ามหนีกองทัพอียิปต์บนดินแห้ง?",
            options = listOf("ทะเลแดง", "ทะเลตาย", "ทะเลกาลิลี", "ทะเลเมดิเตอร์เรเนียน"),
            correctIndex = 0,
            explanation = "โมเสสยื่นมือออกเหนือทะเล และพระยาห์เวห์ทรงบันดาลให้ลมทิศตะวันออกพัดตลอดคืนจนน้ำแยกออก คนอิสราเอลจึงเดินข้ามทะเลบนดินแห้ง",
            scriptureReference = "อพยพ 14:21-22 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 4,
            category = QuizCategory.OLD_TESTAMENT,
            question = "พระบัญญัติสิบประการประทานแก่โมเสส ณ ภูเขาใด?",
            options = listOf("ภูเขาคาร์เมล", "ภูเขาซีนาย (โฮเรบ)", "ภูเขาเนโบ", "ภูเขาไซออน"),
            correctIndex = 1,
            explanation = "พระยาห์เวห์เสด็จลงมาบนยอดภูเขาซีนาย ทรงเรียกโมเสสขึ้นไปและประทานพระโอวาทบนแผ่นศิลาสองแผ่น",
            scriptureReference = "อพยพ 19:20; 20:1-17 (THSV11)",
            difficulty = QuestionDifficulty.MEDIUM
        ),
        BibleQuestion(
            id = 5,
            category = QuizCategory.OLD_TESTAMENT,
            question = "ใครถูกโยนลงไปในถ้ำสิงโตเพราะอธิษฐานต่อพระเจ้า แต่องค์พระผู้เป็นเจ้าทรงส่งทูตสวรรค์มาปิดปากสิงโตไว้?",
            options = listOf("ดาเนียล", "เยเรมีย์", "เอเสเคียล", "อิสยาห์"),
            correctIndex = 0,
            explanation = "ดาเนียลคุกเข่าลงอธิษฐานและสรรเสริญพระเจ้าวันละสามครั้ง จึงถูกใส่ร้ายและโยนลงถ้ำสิงโต แต่พระเจ้าทรงปกปักรักษาเขา",
            scriptureReference = "ดาเนียล 6:10, 21-22 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 6,
            category = QuizCategory.OLD_TESTAMENT,
            question = "ผู้เผยพระวจนะท่านใดถูกปลาตัวมหึมากลืนเข้าไปในท้องเป็นเวลา 3 วัน 3 คืนหลังพยายามหนีไปทารชิช?",
            options = listOf("เอลียาห์", "โยนาห์", "อาโมส", "มีคาห์"),
            correctIndex = 1,
            explanation = "โยนาห์หนีการทรงเรียกของพระยาห์เวห์ที่จะให้ไปเตือนชาวนีนะเวห์ จึงถูกปลากลืนและสำรอกออกมาบนแผ่นดินแห้งเมื่อเขากลับใจ",
            scriptureReference = "โยนาห์ 1:17; 2:10 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 7,
            category = QuizCategory.OLD_TESTAMENT,
            question = "อาหารอัศจรรย์ที่พระเจ้าประทานจากฟ้าสวรรค์ให้คนอิสราเอลกินในถิ่นทุรกันดารตลอด 40 ปีเรียกว่าอะไร?",
            options = listOf("มานา", "มะเดื่อ", "ขนมปังไร้เชื้อ", "ผลทับทิม"),
            correctIndex = 0,
            explanation = "มานามีลักษณะเป็นเกล็ดบางเหมือนน้ำค้างแข็ง เมล็ดเหมือนเมล็ดผักชีสีขาว รสเหมือนขนมแผ่นผสมน้ำผึ้ง",
            scriptureReference = "อพยพ 16:14-15; 31 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 8,
            category = QuizCategory.OLD_TESTAMENT,
            question = "กษัตริย์องค์ใดทูลขอสติปัญญาจากพระเจ้าในการพิพากษาปกครอง และได้ทรงสร้างพระวิหารแห่งแรกในเยรูซาเล็ม?",
            options = listOf("ซาอูล", "ดาวิด", "ซาโลมอน", "เฮเซคียาห์"),
            correctIndex = 2,
            explanation = "ซาโลมอนทูลขอใจที่เข้าใจเพื่อจะวินิจฉัยประชากรของพระเจ้า และทรงสร้างพระนิเวศของพระยาห์เวห์อย่างสง่างาม",
            scriptureReference = "1 พงศ์กษัตริย์ 3:9-12; 6:1 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),

        // === พันธสัญญาใหม่ (NEW_TESTAMENT - THSV11) ===
        BibleQuestion(
            id = 9,
            category = QuizCategory.NEW_TESTAMENT,
            question = "พระเยซูคริสต์ทรงประสูติ ณ เมืองใดตามคำพยากรณ์โบราณ?",
            options = listOf("นาซาเร็ธ", "เบธเลเฮม", "เยรูซาเล็ม", "คาเปอรนาอุม"),
            correctIndex = 1,
            explanation = "พระเยซูทรงประสูติในรางหญ้า ณ เมืองเบธเลเฮมในแคว้นยูเดีย สมจริงตามคำเผยของมีคาห์",
            scriptureReference = "ลูกา 2:4-7; มีคาห์ 5:2 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 10,
            category = QuizCategory.NEW_TESTAMENT,
            question = "การอัศจรรย์หมายสำคัญแรกที่พระเยซูทรงกระทำตามบันทึกในพระธรรมยอห์นคืออะไร?",
            options = listOf("เดินบนน้ำ", "เปลี่ยนน้ำเป็นเหล้าองุ่น", "รักษาคนตาบอด", "ชุบชีวิตลาซารัส"),
            correctIndex = 1,
            explanation = "พระเยซูทรงเปลี่ยนน้ำให้เป็นเหล้าองุ่นในงานสมรส ณ หมู่บ้านคานา แคว้นกาลิลี และสำแดงพระสิริของพระองค์",
            scriptureReference = "ยอห์น 2:1-11 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 11,
            category = QuizCategory.NEW_TESTAMENT,
            question = "พระเยซูทรงเลี้ยงคนประมาณ 5,000 คนด้วยขนมปังและปลากี่ตัว?",
            options = listOf("ขนมปัง 5 ก้อน ปลา 2 ตัว", "ขนมปัง 7 ก้อน ปลา 3 ตัว", "ขนมปัง 2 ก้อน ปลา 5 ตัว", "ขนมปัง 12 ก้อน ปลา 1 ตัว"),
            correctIndex = 0,
            explanation = "จากอาหารกลางวันที่มีขนมปังข้าวบาร์เลย์ห้าก้อนกับปลาสองตัว พระเยซูทรงขอบพระคุณพระเจ้าและแจกจ่าย และเก็บเศษที่เหลือได้ 12 ตะกร้า",
            scriptureReference = "มัทธิว 14:17-21; ยอห์น 6:9-13 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 12,
            category = QuizCategory.NEW_TESTAMENT,
            question = "อัครทูตท่านใดกล่าวปฏิเสธว่าไม่รู้จักพระเยซู 3 ครั้งก่อนไก่ขัน?",
            options = listOf("ยูดาส อิสคาริโอท", "เปโตร", "ยอห์น", "โธมัส"),
            correctIndex = 1,
            explanation = "เปโตรปฏิเสธพระองค์ 3 ครั้ง และเมื่อไก่ขันเขาก็ระลึกถึงพระดำรัสของพระเยซู จึงออกไปร้องไห้อย่างขมขื่น",
            scriptureReference = "มัทธิว 26:69-75 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 13,
            category = QuizCategory.NEW_TESTAMENT,
            question = "พระเยซูทรงฟื้นคืนพระชนม์ขึ้นมาจากความตายในวันที่เท่าใดตามพระคัมภีร์?",
            options = listOf("วันที่ 2", "วันที่ 3", "วันที่ 7", "วันที่ 40"),
            correctIndex = 1,
            explanation = "พระเยซูทรงคืนพระชนม์ในวันที่สาม มีชัยชนะเหนือความตายและหลุมฝังศพเพื่อประทานชีวิตนิรันดร์แก่ผู้เชื่อ",
            scriptureReference = "1 โครินธ์ 15:4; มัทธิว 28:1-6 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 14,
            category = QuizCategory.NEW_TESTAMENT,
            question = "พระวิญญาณบริสุทธิ์เสด็จลงมาสถิตเหนือบรรดาผู้เชื่อในวันสำคัญใด?",
            options = listOf("วันเพนเทคอสต์", "วันปัสกา", "วันลบมลทิน", "วันอยู่เพิง"),
            correctIndex = 0,
            explanation = "ในวันเพนเทคอสต์ เกิดเสียงกระหน่ำเหมือนลมกล้า และเห็นเปลวไฟรูปร่างเหมือนลิ้นแยกกระจายมาอยู่เหนือพวกเขาทุกคน",
            scriptureReference = "กิจการ 2:1-4 (THSV11)",
            difficulty = QuestionDifficulty.MEDIUM
        ),
        BibleQuestion(
            id = 15,
            category = QuizCategory.NEW_TESTAMENT,
            question = "คำอุปมาใดที่พระเยซูทรงสอนถึงคนต่างชาติผู้มีความเมตตาต่อผู้ที่ถูกผู้ร้ายทำร้ายบาดเจ็บข้างทาง?",
            options = listOf("ผู้หว่านพืช", "บุตรน้อยหลงหาย", "ชาวสะมาเรียใจดี", "คนต้นเรือนสัตย์ซื่อ"),
            correctIndex = 2,
            explanation = "ชาวสะมาเรียผู้ซึ่งคนยิวรังเกียจ กลับเป็นคนที่มีความเมตตาพันแผลและส่งคนเจ็บไปรักษาที่โรงแรม",
            scriptureReference = "ลูกา 10:25-37 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 16,
            category = QuizCategory.NEW_TESTAMENT,
            question = "พระธรรมเล่มสุดท้ายในพระคัมภีร์พันธสัญญาใหม่คือเล่มใด?",
            options = listOf("ยูดา", "วิวรณ์", "ฮีบรู", "กิจการ"),
            correctIndex = 1,
            explanation = "พระธรรมวิวรณ์เขียนโดยอัครทูตยอห์นขณะถูกเนรเทศอยู่ที่เกาะปัทมอส เป็นการเปิดเผยถึงชัยชนะของพระเมษโปดกและฟ้าสวรรค์ใหม่และแผ่นดินโลกใหม่",
            scriptureReference = "วิวรณ์ 1:1-2; 21:1 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),

        // === บุคคลสำคัญในพระคัมภีร์ (PEOPLE - THSV11) ===
        BibleQuestion(
            id = 17,
            category = QuizCategory.PEOPLE,
            question = "ใครได้รับการขนานนามว่าเป็น 'บิดาของบรรดาผู้เชื่อ' และพระเจ้าทรงสัญญาว่าเชื้อสายของเขาจะมากมายดังดวงดาวในท้องฟ้า?",
            options = listOf("โนอาห์", "อับราฮัม", "ยาโคบ", "อิสอัค"),
            correctIndex = 1,
            explanation = "อับราฮัมเชื่อฟังการทรงเรียกของพระยาห์เวห์ ออกเดินทางโดยไม่รู้ว่าจะไปที่ไหน และเชื่อในพระสัญญาจึงนับว่าเป็นความชอบธรรม",
            scriptureReference = "ปฐมกาล 15:5-6; โรม 4:11 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 18,
            category = QuizCategory.PEOPLE,
            question = "เด็กหนุ่มผู้เลี้ยงแกะที่เอาชนะยักษ์โกลิอัทด้วยสลิงกับหินเกลี้ยงหนึ่งก้อนคือใคร?",
            options = listOf("โยนาธาน", "ซัมสัน", "ดาวิด", "กิเดโอน"),
            correctIndex = 2,
            explanation = "ดาวิดเข้าสู้กับโกลิอัทในพระนามของพระยาห์เวห์จอมทัพ และใช้หินเกลี้ยงจากลำธารสลัดใส่หน้าผากของโกลิอัทจนล้มลง",
            scriptureReference = "1 ซามูเอล 17:45-50 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 19,
            category = QuizCategory.PEOPLE,
            question = "หญิงสาวชาวโมอับผู้กล่าวแก่แม่สามีว่า 'พระเจ้าของแม่จะเป็นพระเจ้าของฉัน' และกลายเป็นบรรพบุรุษของกษัตริย์ดาวิดคือใคร?",
            options = listOf("เอสเธอร์", "รูธ", "ราเชล", "เลอาห์"),
            correctIndex = 1,
            explanation = "รูธแสดงความรักและความสัตย์ซื่อต่อนาโอมี และต่อมาได้แต่งงานกับโบอาสในเบธเลเฮม",
            scriptureReference = "รูธ 1:16; 4:18-22 (THSV11)",
            difficulty = QuestionDifficulty.MEDIUM
        ),
        BibleQuestion(
            id = 20,
            category = QuizCategory.PEOPLE,
            question = "หญิงชาวยิวผู้ได้เป็นราชินีแห่งเปอร์เซียและเสี่ยงชีวิตเข้าเฝ้ากษัตริย์เพื่อช่วยชนชาติยิวให้รอดพ้นคือใคร?",
            options = listOf("เดโบราห์", "มิเรียม", "เอสเธอร์", "ฮันนาห์"),
            correctIndex = 2,
            explanation = "พระนางเอสเธอร์กล่าวว่า 'ถ้าข้าพเจ้าต้องพินาศ ข้าพเจ้าก็ยอมพินาศ' และปกป้องประชากรยิวจากแผนการชั่วร้ายของฮามาน",
            scriptureReference = "เอสเธอร์ 4:14-16 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 21,
            category = QuizCategory.PEOPLE,
            question = "ใครเคยข่มเหงคริสตจักร แต่ได้พบพระเยซูระหว่างทางไปเมืองดามัสกัส และกลับใจกลายเป็นอัครทูตผู้ยิ่งใหญ่?",
            options = listOf("บารนาบัส", "เปาโล (เซาโล)", "สิลาส", "ทิโมธี"),
            correctIndex = 1,
            explanation = "เซาโลผู้มีใจดุร้ายต่อผู้เชื่อ ได้เห็นแสงสว่างจ้าจากฟ้าสวรรค์และกลับใจกลายเป็นอัครทูตเปาโลผู้นำข่าวประเสริฐไปถึงคนต่างชาติ",
            scriptureReference = "กิจการ 9:3-6 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 22,
            category = QuizCategory.PEOPLE,
            question = "ชายผู้มีพละกำลังมหาศาลซึ่งพลังของเขาผูกพันกับคำปฏิญาณนาศีร์และผมที่ไม่ได้ตัดคือใคร?",
            options = listOf("เยฟธาห์", "ซัมสัน", "กิเดโอน", "บาราค"),
            correctIndex = 1,
            explanation = "ซัมสันเป็นผู้วินิจฉัยของอิสราเอล พระวิญญาณของพระเจ้าสถิตกับเขาเมื่อรักษาคำปฏิญาณการเป็นนาศีร์",
            scriptureReference = "ผู้วินิจฉัย 16:17 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 23,
            category = QuizCategory.PEOPLE,
            question = "ผู้ที่ถูกพี่ชายขายไปเป็นทาสในอียิปต์เพราะความอิจฉา แต่ต่อมาพระเจ้าทรงยกชูให้เป็นผู้ว่าการทั่วแผ่นดินอียิปต์คือใคร?",
            options = listOf("เบนยามิน", "โยเซฟ", "รูเบน", "ยูดาห์"),
            correctIndex = 1,
            explanation = "โยเซฟกล่าวแก่พี่ชายว่า 'พวกพี่คิดร้ายต่อฉันก็จริง แต่ฝ่ายพระเจ้าทรงดำริให้เกิดผลดี เพื่อช่วยชีวิตคนเป็นอันมาก'",
            scriptureReference = "ปฐมกาล 37:28; 50:20 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),

        // === ข้อพระคัมภีร์ & เกร็ดน่ารู้ (VERSES_TRIVIA - THSV11) ===
        BibleQuestion(
            id = 24,
            category = QuizCategory.VERSES_TRIVIA,
            question = "พระคริสตธรรมคัมภีร์ฉบับมาตรฐาน (THSV11) ประกอบด้วยพระธรรมทั้งหมดกี่เล่ม?",
            options = listOf("66 เล่ม (39 + 27)", "70 เล่ม (40 + 30)", "60 เล่ม (35 + 25)", "72 เล่ม (45 + 27)"),
            correctIndex = 0,
            explanation = "พระคัมภีร์โปรเตสแตนต์มี 66 เล่ม แบ่งเป็นพันธสัญญาเดิม 39 เล่ม และพันธสัญญาใหม่ 27 เล่ม",
            scriptureReference = "โครงสร้างพระคัมภีร์ (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 25,
            category = QuizCategory.VERSES_TRIVIA,
            question = "พระธรรมบทใดในพระคัมภีร์ที่มีความยาวมากที่สุด (176 ข้อ)?",
            options = listOf("สดุดี 23", "สดุดี 119", "อิสยาห์ 53", "มัทธิว 5"),
            correctIndex = 1,
            explanation = "สดุดี 119 มีความยาว 176 ข้อ แต่ละวรรคสะท้อนถึงความรักและความล้ำค่าของพระวจนะและพระบัญชาของพระยาห์เวห์",
            scriptureReference = "สดุดี 119 (THSV11)",
            difficulty = QuestionDifficulty.MEDIUM
        ),
        BibleQuestion(
            id = 26,
            category = QuizCategory.VERSES_TRIVIA,
            question = "ประโยคแรกสุดในพระคริสตธรรมคัมภีร์ (ปฐมกาล 1:1) ตามฉบับมาตรฐาน THSV11 เริ่มต้นว่าอย่างไร?",
            options = listOf(
                "ในปฐมกาล พระเจ้าทรงเนรมิตสร้างฟ้าและแผ่นดิน",
                "ในปฐมกาลพระเจ้าทรงสร้างฟ้าและแผ่นดิน",
                "พระเจ้าตรัสว่าจงเกิดความสว่าง",
                "ในปฐมกาลมีพระวาทะ"
            ),
            correctIndex = 0,
            explanation = "ปฐมกาล 1:1 ในฉบับมาตรฐาน THSV11 บันทึกว่า 'ในปฐมกาล พระเจ้าทรงเนรมิตสร้างฟ้าและแผ่นดิน'",
            scriptureReference = "ปฐมกาล 1:1 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 27,
            category = QuizCategory.VERSES_TRIVIA,
            question = "ข้อความที่ว่า 'จงขอแล้วจะได้ จงหาแล้วจะพบ จงเคาะแล้วจะเปิดให้แก่ท่าน' บันทึกอยู่ในพระธรรมใด?",
            options = listOf("มัทธิว 7:7", "มาระโก 1:1", "โรม 8:28", "ยอห์น 14:6"),
            correctIndex = 0,
            explanation = "อยู่ในคำเทศนาบนภูเขาของพระเยซูคริสต์ หนุนใจให้ผู้เชื่อทูลขอสิ่งดีจากพระบิดาแห่งฟ้าสวรรค์ด้วยความเชื่อ",
            scriptureReference = "มัทธิว 7:7 (THSV11)",
            difficulty = QuestionDifficulty.MEDIUM
        ),
        BibleQuestion(
            id = 28,
            category = QuizCategory.VERSES_TRIVIA,
            question = "ตาม 1 โครินธ์ 13 สิ่งใดที่คงอยู่ 3 สิ่ง และสิ่งใดที่ยิ่งใหญ่ที่สุด?",
            options = listOf(
                "ความเชื่อ ความหวังใจ และความรัก (ความรักยิ่งใหญ่ที่สุด)",
                "สติปัญญา ความรู้ และกำลัง (สติปัญญายิ่งใหญ่ที่สุด)",
                "ความรอด สันติสุข และการอัศจรรย์ (ความรอดยิ่งใหญ่ที่สุด)",
                "ความเชื่อ ความสัตย์ซื่อ และความถ่อมใจ (ความเชื่อยิ่งใหญ่ที่สุด)"
            ),
            correctIndex = 0,
            explanation = "1 โครินธ์ 13:13 ระบุว่า 'และบัดนี้ ทั้งสามสิ่งนี้ยังคงอยู่ คือความเชื่อ ความหวังใจ และความรัก แต่ความรักนั้นใหญ่ที่สุด'",
            scriptureReference = "1 โครินธ์ 13:13 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 29,
            category = QuizCategory.VERSES_TRIVIA,
            question = "พระเยซูตรัสกับโธมัสว่า 'เราเป็นทางนั้น เป็นความจริง และเป็น...' สิ่งใด?",
            options = listOf("ความหวัง", "ชีวิต", "สันติสุข", "ความรอด"),
            correctIndex = 1,
            explanation = "ยอห์น 14:6 บันทึกว่า 'พระเยซูตรัสกับเขาว่า เราเป็นทางนั้น เป็นความจริง และเป็นชีวิต ไม่มีใครมาถึงพระบิดาได้นอกจากจะมาทางเรา'",
            scriptureReference = "ยอห์น 14:6 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        ),
        BibleQuestion(
            id = 30,
            category = QuizCategory.VERSES_TRIVIA,
            question = "เมืองใดที่กำแพงพังทลายลงมาหลังจากกองทัพอิสราเอลเดินวนรอบเมือง 7 วันและเป่าเขาสัตว์?",
            options = listOf("เยริโค", "บาบิโลน", "ดามัสกัส", "เยรูซาเล็ม"),
            correctIndex = 0,
            explanation = "โยชูวาและคนอิสราเอลเชื่อฟังพระเจ้า เดินวนรอบเมืองเยริโค เมื่อปุโรหิตเป่าเขาสัตว์และทุกคนโห่ร้องกำแพงก็พังทลายลงทันที",
            scriptureReference = "โยชูวา 6:20 (THSV11)",
            difficulty = QuestionDifficulty.EASY
        )
    )

    fun getQuestionsForCategory(category: QuizCategory, count: Int = 10): List<BibleQuestion> {
        val pool = when (category) {
            QuizCategory.ALL, QuizCategory.TIMED_CHALLENGE -> questions
            QuizCategory.OLD_TESTAMENT -> questions.filter { it.category == QuizCategory.OLD_TESTAMENT }
            QuizCategory.NEW_TESTAMENT -> questions.filter { it.category == QuizCategory.NEW_TESTAMENT }
            QuizCategory.PEOPLE -> questions.filter { it.category == QuizCategory.PEOPLE }
            QuizCategory.VERSES_TRIVIA -> questions.filter { it.category == QuizCategory.VERSES_TRIVIA }
        }
        return pool.shuffled().take(count)
    }

    fun getQuestionById(id: Int): BibleQuestion? {
        return questions.find { it.id == id }
    }
}
