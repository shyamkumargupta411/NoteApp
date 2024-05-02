package com.example.noteapp

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    private var _noteList =MutableLiveData<List<Data>>()
    val noteList : LiveData<List<Data>> =_noteList
    init {
        getAllNote()
    }
    fun getAllNote() {
        _noteList.value = NoteManager.getAllNote().reversed()
    }


    fun addNote(title: String){
        NoteManager.addNote(title)
        getAllNote()
    }
    fun deleteNode(id:Int){
        NoteManager.deleteNode(id)
        getAllNote()
    }

}