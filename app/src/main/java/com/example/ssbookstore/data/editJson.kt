package com.example.ssbookstore.data

import android.content.Context
import android.util.Log
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun editJson(context: Context, updatedBook: BookData, filename: String = "bookdata.json") {
    val bookList = readJson(context, filename)

    val newBookList = bookList.map {
        if (it.bookId == updatedBook.bookId) updatedBook else it
    }

    val file = File(context.filesDir, filename)
    val updatedJson = Json.encodeToString<List<BookData>>(ListSerializer(BookData.serializer()), newBookList)
    file.writeText(updatedJson)

    val currentList: MutableList<BookData> = Json.decodeFromString(file.readText())
    val sortedList = currentList.sortedByDescending { it.timestamp }
    val jsonData = Json.encodeToString(sortedList)

    Log.i("editJson", "writing")
    file.writeText(jsonData)
    Log.i("editJson", "Data: $jsonData")
}