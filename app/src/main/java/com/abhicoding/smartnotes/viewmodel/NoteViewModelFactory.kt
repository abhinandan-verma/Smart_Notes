package com.abhicoding.smartnotes.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abhicoding.smartnotes.repository.Repository

class NoteViewModelFactory(val application: Application, private val repository: Repository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(application, repository)as T
    }
}