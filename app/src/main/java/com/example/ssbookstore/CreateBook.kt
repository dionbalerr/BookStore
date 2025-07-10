package com.example.ssbookstore

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ssbookstore.data.BookData
import com.example.ssbookstore.data.copyToInternalStorage
import com.example.ssbookstore.data.addJson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBook(navController: NavController) {
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

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
                            containerColor = Color.Companion.Blue
                        ),
                        onClick = {
                            if (name.isNotBlank() && imageUri.value != null) {
                                val newUri =
                                    imageUri.value?.let { copyToInternalStorage(context, it) }
                                val newBook = BookData(
                                    imgId = newUri?.toString(),
                                    bookName = name,
                                    authorName = author,
                                    description = description
                                )

                                addJson(context, newBook)
//                                Log.i("Saving data", "File Saved!")

                                navController.navigate("listing-screen")
                                Toast.makeText(context, "Book Added!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Book name/image cannot be empty", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                        Text(text = "Add book")
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

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.PickVisualMedia(),
                        onResult = { uri -> imageUri.value = uri }
                    )

                    if (imageUri.value != null) {
                        Log.i("Image", "Image is: ${imageUri.value}")
                        AsyncImage(
                            model = imageUri.value,
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .padding(2.dp)
                                .height(300.dp)
                                .width(200.dp)
                                .border(1.dp, Color.Black)
                                .clickable {
                                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                },
                            contentScale = ContentScale.Crop)
                    } else {
                        OutlinedButton(onClick = {
                            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                            modifier = Modifier.border(1.dp, Color.Black),
                            shape = RectangleShape
                        ) {
                            Text(text = "Choose an Image")
                        }
                    }

                    OutlinedTextField(
                        modifier = Modifier.padding(2.dp),
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") }
                    )

                    OutlinedTextField(
                        modifier = Modifier.padding(2.dp),
                        value = author,
                        onValueChange = { author = it },
                        label = { Text("Author") }
                    )

                    OutlinedTextField(
                        modifier = Modifier.padding(2.dp),
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        minLines = 3,
                        maxLines = 5
                    )
                }
            }
        }
    }
}

