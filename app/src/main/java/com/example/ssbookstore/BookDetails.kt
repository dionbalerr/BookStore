package com.example.ssbookstore

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ssbookstore.data.BookData
import com.example.ssbookstore.data.readJson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetails(bookId: String?, navController: NavController) {
    Log.i("BookId", "Bookid is $bookId")

    val context = LocalContext.current
    val bookList = remember {mutableStateListOf<BookData>()}

    LaunchedEffect(Unit) {
        val result = readJson(context)
        bookList.clear()
        bookList.addAll(result)
    }

    val targetBook = bookList.find {it.bookId == bookId}
    Log.i("BookId", "TargetBook is $targetBook")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(text = "${targetBook?.bookName}")
                },

                navigationIcon = {
                    TextButton(
                        modifier = Modifier.Companion
                            .padding(5.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Companion.White,
                            containerColor = Color.Companion.Blue
                        ),
                        onClick = {
                            navController.popBackStack()
                        }) {
                        Text(text = "Back")
                    }
                },

                actions = {
                    TextButton(
                        modifier = Modifier.Companion
                            .padding(5.dp)
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(5.dp)),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Companion.White,
                            containerColor = Color.Companion.Green
                        ),
                        onClick = {
                            navController.navigate("edit-screen/${targetBook?.bookId}")
                        }) {
                        Text(text = "Edit")
                    }
                }

            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val imageUri = targetBook?.imgId

                    if (imageUri != null) {
                        AsyncImage(
                            model = imageUri,
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .padding(2.dp)
                                .height(300.dp)
                                .width(200.dp)
                                .border(1.dp, Color.Black),
                            contentScale = ContentScale.Crop)
                    }

                    OutlinedTextField(
                        modifier = Modifier.padding(2.dp),
                        value = "${targetBook?.bookName}",
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Name") }
                    )

                    OutlinedTextField(
                        modifier = Modifier.padding(2.dp),
                        value = "${targetBook?.authorName}",
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Author") }
                    )

                    OutlinedTextField(
                        modifier = Modifier.padding(2.dp),
                        value = "${targetBook?.description}",
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Description") },
                        minLines = 3,
                        maxLines = 5
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookDet() {
//    BookDetails()
}