package com.abhicoding.smartnotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.abhicoding.smartnotes.repository.Repository
import com.abhicoding.smartnotes.room.Note
import kotlinx.coroutines.launch

class NoteViewModel(app: Application,private val repository: Repository)
    :AndroidViewModel(app){
        fun insertNote(note: Note) = viewModelScope.launch {
            repository.insertNote(note)
        }
        fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
        }
        fun updateNote(note: Note) = viewModelScope.launch {
            repository.updateNote(note)
        }

        fun getAllNotes() = repository.getAllNotes()
        fun searchNote(query: String?) : LiveData<List<Note>>{
            return repository.searchNote(query)
        }
}