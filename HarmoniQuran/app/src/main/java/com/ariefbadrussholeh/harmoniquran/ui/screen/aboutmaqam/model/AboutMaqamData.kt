package com.ariefbadrussholeh.harmoniquran.ui.screen.aboutmaqam.model

import com.ariefbadrussholeh.harmoniquran.R

data class ToneLevelData (
    val title: String,
    val description: String,
    val text: List<String>,
    val audioPath: Int,
)

data class AboutMaqamData (
    val title: String,
    val description: String,
    val toneLevels: List<ToneLevelData>
)

val aboutMaqamData = listOf(
    AboutMaqamData(
        title = "Bayati",
        description = "Maqam bayati adalah salah satu maqam yang paling populer di Mesir. Maqam ini sering digunakan untuk memulai dan mengakhiri bacaan tilawah mujawwad, menjadikannya standar dalam tradisi melagukan Al-Qur’an. Dalam Musabaqah Tilawatil Qur’an (MTQ), maqam bayati wajib dibawakan sebagai maqam pembuka dan penutup. Kata \"bayati\" berasal dari kata \"bayt\" yang berarti rumah atau tempat kembali. Maqam ini memiliki karakter yang lembut, fleksibel, dan sering dianggap lebih feminim. Nada-nadanya bisa dinaikkan dan diturunkan dengan mudah. Bayati mengandung kombinasi nada kesedihan, kerinduan, kesenangan, dan kebahagiaan dalam satu kesatuan.",
        toneLevels = listOf(
            ToneLevelData(
                title = "Bayati Qoror",
                description = "Q.S. Ar-Rahman (55): 1-4",
                text = listOf("أَعُوذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيم ", "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّحِيْمِ ", "اَلرَّحْمٰنُۙ ", "عَلَّمَ الْقُرْاٰنَۗ ", "خَلَقَ الْاِنْسَانَۙ ", "عَلَّمَهُ الْبَيَانَ "),
                audioPath = R.raw.bayati_qoror
            ),
            ToneLevelData(
                title = "Bayati Nawa",
                description = "Q.S. Ar-Rahman (55): 5-6",
                text = listOf("اَلشَّمْسُ وَالْقَمَرُ بِحُسْبَانٍۙ", "وَّالنَّجْمُ وَالشَّجَرُ يَسْجُدَانِ"),
                audioPath = R.raw.bayati_nawa
            ),
            ToneLevelData(
                title = "Bayati Suri",
                description = "Q.S. Ar-Rahman (55): 7",
                text = listOf("وَالسَّمَاۤءَ رَفَعَهَا وَوَضَعَ الْمِيْزَانَۙ"),
                audioPath = R.raw.bayati_suri
            ),
            ToneLevelData(
                title = "Bayati Husaini",
                description = "Q.S. Ar-Rahman (55): 7-9",
                text = listOf("وَالسَّمَاۤءَ رَفَعَهَا وَوَضَعَ الْمِيْزَانَۙ", "اَلَّا تَطْغَوْا فِى الْمِيْزَانِ", "وَاَقِيْمُوا الْوَزْنَ بِالْقِسْطِ وَلَا تُخْسِرُوا الْمِيْزَانَ"),
                audioPath = R.raw.bayati_husaini
            ),
            ToneLevelData(
                title = "Bayati Jawab",
                description = "Q.S. Ar-Rahman (55): 10-11",
                text = listOf("وَالْاَرْضَ وَضَعَهَا لِلْاَنَامِۙ", "فِيْهَا فَاكِهَةٌ وَّالنَّخْلُ ذَاتُ الْاَكْمَامِۖ"),
                audioPath = R.raw.bayati_jawab
            ),
            ToneLevelData(
                title = "Bayati Jawabul Jawab",
                description = "Q.S. Ar-Rahman (55): 12-13",
                text = listOf("وَالْحَبُّ ذُو الْعَصْفِ وَالرَّيْحَانُۚ", "فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ"),
                audioPath = R.raw.bayati_jawabuljawab
            ),
        )
    ),
    AboutMaqamData(
        title = "Saba",
        description = "Saba adalah maqam yang memiliki karakter halus dan lembut. Nama “saba” terinspirasi dari waktu Subuh dalam kehidupan sehari-hari. Maqam ini cenderung lebih mendatar, tapi bisa menyampaikan kesedihan, penderitaan, dan kesakitan yang dapat menggerakkan emosi pendengar. Oleh karena itu, saba sering dikaitkan dengan ayat-ayat yang berisi ancaman, penyesalan, dan permohonan perlindungan. Orang Arab menyebutnya sebagai Maqam al-Buka’ (lagu tangisan). Jika seorang qari yang sedang dalam suasana hati sentimental membawakan maqam ini, ayat-ayat Al-Qur’an yang dibacakan akan terasa lebih mendalam.",
        toneLevels = listOf(
            ToneLevelData(
                title = "Saba Asli",
                description = "Q.S. Ar-Rahman (55): 14-15",
                text = listOf("خَلَقَ الْاِنْسَانَ مِنْ صَلْصَالٍ كَالْفَخَّارِ", "وَخَلَقَ الْجَاۤنَّ مِنْ مَّارِجٍ مِّنْ نَّارٍۚ"),
                audioPath = R.raw.saba_asli
            ),
            ToneLevelData(
                title = "Saba Jawab",
                description = "Q.S. Ar-Rahman (55): 15",
                text = listOf("وَخَلَقَ الْجَاۤنَّ مِنْ مَّارِجٍ مِّنْ نَّارٍۚ"),
                audioPath = R.raw.saba_jawab
            ),
            ToneLevelData(
                title = "Saba Jawab Ma'al Ajam",
                description = "Q.S. Ar-Rahman (55): 14-15",
                text = listOf("خَلَقَ الْاِنْسَانَ مِنْ صَلْصَالٍ كَالْفَخَّارِ", "وَخَلَقَ الْجَاۤنَّ مِنْ مَّارِجٍ مِّنْ نَّارٍۚ"),
                audioPath = R.raw.saba_jawab_maalajam
            ),
            ToneLevelData(
                title = "Saba Jawab Ma'al Bastanjar",
                description = "Q.S. Ar-Rahman (55): 15",
                text = listOf("وَخَلَقَ الْجَاۤنَّ مِنْ مَّارِجٍ مِّنْ نَّارٍۚ"),
                audioPath = R.raw.saba_jawab_maalbastanjar
            ),
        )
    ),
    AboutMaqamData(
        title = "Hijaz",
        description = "Maqam hijaz memiliki ciri khas ketimuran dengan nuansa yang sangat indah. Bertempo lincah, cepat, dan dengan perubahan nada yang tajam, maqam ini memiliki seni tersendiri. Maqam ini juga diketahui sering digunakan oleh orang-orang Hijaz (tempat terletaknya kota suci Islam Mekkah dan Madinah) untuk menggembala unta di padang pasir. Meskipun pada dasarnya maqam hijaz merupakan bagian dari musik Makkawi yang berkembang di Mesir, hijaz sendiri memiliki banyak variasi karena ruang lingkup nada dan iramanya yang luas. Namun, tidak semua orang dapat menguasai maqam hijaz karena memerlukan nada tinggi dan sifatnya yang kompleks.",
        toneLevels = listOf(
            ToneLevelData(
                title = "Hijaz Asli",
                description = "Q.S. Ar-Rahman (55): 16",
                text = listOf("فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ"),
                audioPath = R.raw.hijaz_asli
            ),
            ToneLevelData(
                title = "Hijaz Kar",
                description = "Q.S. Ar-Rahman (55): 17-18",
                text = listOf("رَبُّ الْمَشْرِقَيْنِ وَرَبُّ الْمَغْرِبَيْنِۚ", "فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ"),
                audioPath = R.raw.hijaz_kar
            ),
            ToneLevelData(
                title = "Hijaz Karkur",
                description = "Q.S. Ar-Rahman (55): 18",
                text = listOf("فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ"),
                audioPath = R.raw.hijaz_karkur
            ),
        )
    ),
    AboutMaqamData(
        title = "Rast",
        description = "Rast adalah salah satu jenis maqam yang dominan dan sering dianggap sebagai lagu dasar. Maqam ini sering digunakan dalam kegiatan seperti barzanji, mengumandangkan adzan, dan bacaan tarhim karena sifatnya yang dinamis dan penuh semangat",
        toneLevels = listOf(
            ToneLevelData(
                title = "Rast Asli",
                description = "Q.S. Ar-Rahman (55): 19-20",
                text = listOf("مَرَجَ الْبَحْرَيْنِ يَلْتَقِيٰنِۙ", "بَيْنَهُمَا بَرْزَخٌ لَّا يَبْغِيٰنِۚ"),
                audioPath = R.raw.rast_asli
            ),
            ToneLevelData(
                title = "Rast Zinjiran",
                description = "Q.S. Ar-Rahman (55): 21",
                text = listOf("بَيْنَهُمَا بَرْزَخٌ لَّا يَبْغِيٰنِۚ", "فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ"),
                audioPath = R.raw.rast_zinjiran
            ),
            ToneLevelData(
                title = "Rast Alan Nawa",
                description = "Q.S. Ar-Rahman (55): 22-23",
                text = listOf("يَخْرُجُ مِنْهُمَا اللُّؤْلُؤُ وَالْمَرْجَانُۚ", "فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ"),
                audioPath = R.raw.rast_alannawa
            ),
        )
    ),
    AboutMaqamData(
        title = "Sikah",
        description = "Sikah memiliki karakteristik ketimuran yang merakyat, mudah dikenali, dan sangat populer di kalangan penduduk Mesir. Maqam ini terkenal dengan alunan yang cemerlang. Meskipun memiliki nuansa kesedihan, maqam ini tidak mencerminkan ketidakberdayaan. Maqam sikah bisa diibaratkan seperti seorang pendosa yang meratapi dosanya di masa lalu.",
        toneLevels = listOf(
            ToneLevelData(
                title = "Sikah Asli",
                description = "Q.S. Ar-Rahman (55): 24",
                text = listOf("وَلَهُ الْجَوَارِ الْمُنْشَاٰتُ فِى الْبَحْرِ كَالْاَعْلَامِۚ"),
                audioPath = R.raw.sikah_asli
            ),
            ToneLevelData(
                title = "Sikah Misri",
                description = "Q.S. Ar-Rahman (55): 25",
                text = listOf("فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ ࣖ"),
                audioPath = R.raw.sikah_misri
            ),
            ToneLevelData(
                title = "Sikah Turki",
                description = "Q.S. Ar-Rahman (55): 26-27",
                text = listOf("كُلُّ مَنْ عَلَيْهَا فَانٍۖ", "وَّيَبْقٰى وَجْهُ رَبِّكَ ذُو الْجَلٰلِ وَالْاِكْرَامِۚ"),
                audioPath = R.raw.sikah_turki
            ),
        )
    ),
    AboutMaqamData(
        title = "Jiharkah",
        description = "Maqam Jiharkah ini memiliki irama mayor, yang terdengar manis dan menimbulkan perasaan yang mendalam. Maqam ini memiliki karakter yang halus, lembut, dan mendayu-dayu dengan nada yang terdengar manja, mirip seorang kekasih yang sedang merayu pasangannya. Maqam ini juga bisa diibaratkan seperti seorang tua yang bahagia menyaksikan anak-cucunya.",
        toneLevels = listOf(
            ToneLevelData(
                title = "Jiharkah Asli",
                description = "Q.S. Ar-Rahman (55): 28-29",
                text = listOf("فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ", "يَسْـَٔلُهٗ مَنْ فِى السَّمٰوٰتِ وَالْاَرْضِۗ كُلَّ يَوْمٍ هُوَ فِيْ شَأْنٍۚ"),
                audioPath = R.raw.jiharkah_asli
            ),
            ToneLevelData(
                title = "Jiharkah Jawab",
                description = "Q.S. Ar-Rahman (55): 29-30",
                text = listOf("يَسْـَٔلُهٗ مَنْ فِى السَّمٰوٰتِ وَالْاَرْضِۗ كُلَّ يَوْمٍ هُوَ فِيْ شَأْنٍۚ", "فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ"),
                audioPath = R.raw.jiharkah_jawab
            ),
        )
    ),
    AboutMaqamData(
        title = "Nahawand",
        description = "Nahawand memiliki irama minor, yang terdengar sedih, lembut dan indah. Maqam ini sering kali disenandungkan pada ayat-ayat Al-Qur’an yang sedih.",
        toneLevels = listOf(
            ToneLevelData(
                title = "Nahawand Asli",
                description = "Q.S. Ar-Rahman (55): 31-32",
                text = listOf(
                    "سَنَفْرُغُ لَكُمْ اَيُّهَ الثَّقَلٰنِۚ",
                    "فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ"
                ),
                audioPath = R.raw.nahawand_asli
            ),
            ToneLevelData(
                title = "Nahawand Nawa",
                description = "Q.S. Ar-Rahman (55): 33",
                text = listOf(
                    "يٰمَعْشَرَ الْجِنِّ وَالْاِنْسِ اِنِ اسْتَطَعْتُمْ اَنْ تَنْفُذُوْا مِنْ اَقْطَارِ السَّمٰوٰتِ وَالْاَرْضِ فَانْفُذُوْاۗ لَا تَنْفُذُوْنَ اِلَّا بِسُلْطٰنٍۚ"
                ),
                audioPath = R.raw.nahawand_nawa
            ),
            ToneLevelData(
                title = "Nahawand Jawab",
                description = "Q.S. Ar-Rahman (55): 33",
                text = listOf(
                    "يٰمَعْشَرَ الْجِنِّ وَالْاِنْسِ اِنِ اسْتَطَعْتُمْ اَنْ تَنْفُذُوْا مِنْ اَقْطَارِ السَّمٰوٰتِ وَالْاَرْضِ فَانْفُذُوْاۗ لَا تَنْفُذُوْنَ اِلَّا بِسُلْطٰنٍۚ"
                ),
                audioPath = R.raw.nahawand_jawab
            ),
            ToneLevelData(
                title = "Penutup (Bayati)",
                description = "Q.S. Ar-Rahman (55): 34",
                text = listOf(
                    "فَبِاَيِّ اٰلَاۤءِ رَبِّكُمَا تُكَذِّبٰنِ",
                    "صَدَقَ اللهُ اْلعَظِيْمُ"
                ),
                audioPath = R.raw.penutup_bayati
            )
        )
    ),
)


