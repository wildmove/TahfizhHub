package com.example.tahfizhhub

import com.example.tahfizhhub.model.SetoranData

data class AddUIState(
    val addEvent: AddEvent = AddEvent(),
)

data class AddEvent(
    var setoranID: String = "",
    var juz: String = "",
    var surat: String = "",
    val ayat: String = "",
    var halaman: String = "",
    var timestamp: Any? = null
)

fun AddEvent.toSetoranData() = SetoranData(
    setoranID = setoranID,
    juz = juz,
    surat = surat,
    ayat = ayat,
    halaman = halaman,
)

data class DetailUIState(
    val addEvent: AddEvent = AddEvent()
)

fun SetoranData.toDetailSetoranData(): AddEvent =
    AddEvent(
        setoranID = setoranID,
        juz = juz,
        surat = surat,
        ayat = ayat,
        halaman = halaman,
    )

fun SetoranData.toUIStateSetoranData(): AddUIState = AddUIState(
    addEvent = this.toDetailSetoranData()
)

data class HomeUIState(
    val listSetoran: List<SetoranData> = listOf(),
    val dataLength: Int = 0
)