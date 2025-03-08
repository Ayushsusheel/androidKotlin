package com.example.first_app_humblecoder

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun encoderDecoder()
{

    var inputEncode by remember { mutableStateOf("") }
    var encodedResult by remember { mutableStateOf("") }
    var inputDecode by remember { mutableStateOf("") }
    var decodedResult by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        val bg1 = painterResource(id= R.mipmap.background_foreground)
        Image(
            painter = bg1, // Replace with your actual drawable resource
            contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.15f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Encoder/Decoder App",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 60.dp, bottom = 16.dp)
            )

            OutlinedTextField
            (
                value = inputEncode,
                onValueChange = { inputEncode = it },
                label = { Text("Enter a string to encode") },
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = { encodedResult = encode(inputEncode) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Encode")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SelectionContainer {
                    Text(
                        text = "Encoded: $encodedResult",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(1f)
                    )
                }
                Button(colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = { shareText(context, encodedResult) }) {
                    Text("Share")
                }
            }

            OutlinedTextField(
                value = inputDecode,
                onValueChange = { inputDecode = it },
                label = { Text("Enter a string to decode") },
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = { decodedResult = decode(inputDecode) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Decode")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SelectionContainer {
                    Text(
                        text = "Decoded: $decodedResult",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(1f)
                    )
                }
                Button(colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = { shareText(context, decodedResult) }) {
                    Text("Share")
                }
            }
        }
    }

}

fun encode(input: String): String {
    return input.map { char ->
        (char.code + 2).toChar()
    }.joinToString("")
}

fun decode(input: String): String {
    return input.map { char ->
        (char.code - 2).toChar()
    }.joinToString("")
}

fun shareText(context: Context, text: String) {
    if (text.isNotEmpty()) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}
