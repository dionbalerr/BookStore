package com.example.ssbookstore.data

import android.content.Context
import android.util.Log
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun addJson(context: Context, bookdata: BookData, filename: String = "bookdata.json") {
    Log.i("addjson", "start")
    val file = File(context.filesDir, filename)

    try {
        val currentList: MutableList<BookData> = if (file.exists()) {
            try {
                Json.decodeFromString(file.readText())
            } catch (e: Exception) {
                Log.e("addjson", "failed", e)
                mutableListOf()
            }
        } else {
            mutableListOf()
        }

        currentList.add(bookdata)

        // add sorting by timestamp, newest first
        val sortedList = currentList.sortedByDescending { it.timestamp }
        val jsonData = Json.encodeToString(sortedList)

        Log.i("addJson", "writing")
        file.writeText(jsonData)
        Log.i("addJson", "Data: $jsonData")

    } catch (e: Exception) {
        Log.i("addJson", "write fail", e)
    }
}