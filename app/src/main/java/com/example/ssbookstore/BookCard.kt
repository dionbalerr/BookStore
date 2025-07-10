package com.example.ssbookstore

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.example.ssbookstore.data.BookData
import com.example.ssbookstore.data.formatTimestamp

@Composable
fun BookCard(bookData: BookData, onClick: () -> Unit) {
    Surface(shadowElevation = 2.dp,
        modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .wrapContentHeight()
        .border(1.dp, Color.LightGray)
        .clickable { onClick() }
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {

                val (bookImage, bookName, authorName, timestamp) = createRefs()
                val imageUri = bookData.imgId?.toUri()

                AsyncImage(
                    model = imageUri,
                    contentDescription = bookData.bookName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(100.dp)
                        .padding(5.dp)
                        .constrainAs(bookImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                )

                Text(
                    text = bookData.bookName,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(bottom = 1.dp, start = 2.dp)
                        .constrainAs(bookName) {
                            top.linkTo(parent.top)
                            start.linkTo(bookImage.end)
                            centerVerticallyTo(bookImage)
                        })

                Text(
                    text = bookData.authorName,
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(2.dp)
                        .constrainAs(authorName) {
                            top.linkTo(bookName.bottom)
                            start.linkTo(bookImage.end)
                        })

                Text(
                    text = formatTimestamp(bookData.timestamp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .padding(2.dp)
                        .constrainAs(timestamp) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(bookName.end)
                            end.linkTo(parent.end)
                        })
            }
        }
    }


