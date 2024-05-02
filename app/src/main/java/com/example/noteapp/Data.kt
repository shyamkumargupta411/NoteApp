package com.example.noteapp

import androidx.compose.runtime.Composable
import java.time.Instant
import java.util.Date

data class Data(
    var id:Int,
    var title: String,
    var createAt: Date
)


@Composable
fun fake(): List<Data>{
    return listOf<Data>(
        Data(id = 1, title = "shaym",Date.from(Instant.now())),
        Data(id = 2, title = "shaym",Date.from(Instant.now())),
        Data(id = 3, title = "shaym",Date.from(Instant.now())),
    )

}
