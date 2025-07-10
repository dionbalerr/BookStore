package com.example.ssbookstore.data

import android.content.Context
import kotlinx.serialization.json.Json
import java.io.File

fun readJson(context: Context, filename: String = "bookdata.json"): List<BookData> {
    val file = File(context.filesDir, filename)

    return if (file.exists()) {
        try {
            Json.decodeFromString(file.readText())
        } catch (e: Exception) {
            emptyList()
        }
    } else {
        emptyList()
    }
}