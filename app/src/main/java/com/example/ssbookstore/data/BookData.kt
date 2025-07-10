package com.example.ssbookstore.data

import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class BookData(
    val bookId: String = UUID.randomUUID().toString(),
    val imgId: String?,
    val bookName: String,
    val authorName: String,
    val description: String,
    val timestamp: String = Instant.now().toString()
)
