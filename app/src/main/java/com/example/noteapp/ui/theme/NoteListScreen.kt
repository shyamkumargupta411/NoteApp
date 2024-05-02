package com.example.noteapp.ui.theme

import android.content.ClipData.Item
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.Data
import com.example.noteapp.NoteViewModel
import com.example.noteapp.fake
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NoteList(viewModel: NoteViewModel){
    val noteList by viewModel.noteList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(value = inputText, onValueChange = {
                inputText =it
            })
            Button(onClick = { viewModel.addNote(inputText)
            inputText =""}) {
                Text(text = "ADD")

            }
        }
        noteList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it){index: Int, item: Data ->
                        NoteItem(item = item,onDelete = {
                            viewModel.deleteNode(item.id)
                        }
                        )
                    }
                }
            )
        }

    }

}
@Composable
fun NoteItem(item : Data,onDelete : ()-> Unit){
    val viewModel = viewModel<NoteViewModel>()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.createAt),
            fontSize = 10.sp,
                color = Color.LightGray
            )
            Text(text = item.title,
                fontSize = 20.sp,
                color = Color.White
                )
        }
        IconButton(onClick = {viewModel.deleteNode(item.id)}) {
            Text(text = "DEL")
        }


    }
}