package com.abhicoding.smartnotes.repository

import com.abhicoding.smartnotes.room.Note
import com.abhicoding.smartnotes.room.NoteDatabase

class Repository(private val database: NoteDatabase) {

    suspend fun insertNote(note: Note) = database.getNodeDao().insertNote(note)
    suspend fun deleteNote(note: Note) = database.getNodeDao().deleteNote(note)
    suspend fun updateNote(note: Note) = database.getNodeDao().updateNote(note)

    fun getAllNotes() = database.getNodeDao().getAllNotes()
    fun searchNote(query : String?) = database.getNodeDao().searchNote(query)
}