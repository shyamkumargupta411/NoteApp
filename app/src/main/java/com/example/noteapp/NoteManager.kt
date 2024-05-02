package com.example.noteapp

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import  java.util.Date

object NoteManager {
    private val noteList = mutableListOf<Data>()
    fun getAllNote() : List<Data>{
        return noteList
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun addNote(title: String) {
        noteList.add(Data(System.currentTimeMillis().toInt(),title,Date.from(Instant.now())))
    }
    fun deleteNode(id: Int){
        noteList.removeIf {
            it.id==id
        }
    }
}

