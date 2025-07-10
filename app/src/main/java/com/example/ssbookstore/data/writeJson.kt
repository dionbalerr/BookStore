package com.example.ssbookstore.data

import android.content.Context
import android.util.Log
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

fun writeJson(context: Context, updatedList: List<BookData>, filename: String = "bookdata.json") {
    val file = File(context.filesDir, filename)

    try {
        val sortedList = updatedList.sortedByDescending { it.timestamp }
        val jsonData = Json.encodeToString<List<BookData>>(ListSerializer(BookData.serializer()), sortedList)

        file.writeText(jsonData)
        Log.i("writeJson", "Updated JSON written:\n$jsonData")
    } catch (e: Exception) {
        Log.e("writeJson", "Error writing file", e)
    }
}