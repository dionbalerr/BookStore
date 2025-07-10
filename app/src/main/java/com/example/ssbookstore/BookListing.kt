package com.example.ssbookstore

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ssbookstore.data.BookData
import com.example.ssbookstore.data.readJson
import com.example.ssbookstore.data.writeJson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListing(navController: NavController) {

    val context = LocalContext.current
    val bookList = remember {mutableStateListOf<BookData>()}

    LaunchedEffect(Unit) {
        val result = readJson(context)
        bookList.clear()
        bookList.addAll(result)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(text = "SS Bookstore")
                },

                navigationIcon = {
                    TextButton(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.White,
                            containerColor = Color.Blue
                        ),
                        onClick = {
                            navController.popBackStack(navController.graph.startDestinationId,
                                inclusive = false)
                        }) {
                        Text(text = "Logout")
                    }
                },

                actions = {
                    IconButton(onClick = {
                        navController.navigate("create-screen")
                    }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    }
                }

            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(bookList) { book ->
                SwipeableBookCard(book = book,
                    onClick = {navController.navigate("details-screen/${book.bookId}")},
                    onDeleteConfirmed = { toDelete ->
                        bookList.remove(toDelete)
                        writeJson(context, updatedList = bookList)
                        Toast.makeText(context, "Book Deleted", Toast.LENGTH_SHORT).show()
                    })
            }
        }
    }
}
