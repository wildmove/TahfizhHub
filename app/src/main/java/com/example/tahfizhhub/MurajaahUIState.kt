package com.example.tahfizhhub

import android.health.connect.datatypes.units.Length
import com.example.tahfizhhub.model.MurajaahData

data class AddMurajaahUIState(
    val addMurajaahEvent: AddMurajaahEvent = AddMurajaahEvent()

)

data class AddMurajaahEvent(
    var murajaahID: String = "",
    val juz: String = "",
    val surat: String = "",
    val ayat: String = "",
    val halaman: String = "",
    var timestamp: Any? = null
)

fun AddMurajaahEvent.toMurajaahData() = MurajaahData(
    murajaahID = murajaahID,
    juz = juz,
    surat = surat,
    ayat = ayat,
    halaman = halaman,
    timestamp = timestamp
)

data class DetailMurajaahUIState(
    val addMurajaahEvent: AddMurajaahEvent = AddMurajaahEvent()
)

fun MurajaahData.toDetailMurajaahData(): AddMurajaahEvent =
    AddMurajaahEvent(
        murajaahID = murajaahID,
        juz = juz,
        surat = surat,
        ayat = ayat,
        halaman = halaman,
        timestamp = timestamp
    )

fun MurajaahData.toUIStateMurajaahData(): AddMurajaahUIState = AddMurajaahUIState(
    addMurajaahEvent = this.toDetailMurajaahData()
)

data class MurajaahUIState(
    val listMurajaahData: List<MurajaahData> = listOf(),
    val dataLength: Int = 0
)