package com.example.ssbookstore.data

import java.time.ZoneId
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatTimestamp(raw: String): String {
    return try {
        val instant = Instant.parse(raw)
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH)
            .withZone(ZoneId.systemDefault())
        formatter.format(instant)
    } catch (e: Exception) {
        "Invalid Date"
    }
}