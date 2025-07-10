package com.example.ssbookstore.data

import android.content.Context
import androidx.core.net.toUri
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

fun copyToInternalStorage(context: Context, sourceUri: Uri): Uri? {
    return try {
        val inputStream = context.contentResolver.openInputStream(sourceUri) ?: return null
        val file = File(context.filesDir, "img_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)

        inputStream.copyTo(outputStream)

        inputStream.close()
        outputStream.close()

        file.toUri()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}