package com.abhicoding.smartnotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.abhicoding.smartnotes.databinding.ActivityMainBinding
import com.abhicoding.smartnotes.repository.Repository
import com.abhicoding.smartnotes.room.NoteDatabase
import com.abhicoding.smartnotes.viewmodel.NoteViewModel
import com.abhicoding.smartnotes.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var notesViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val repository = Repository(NoteDatabase(this))

        val viewModelFactory
                = NoteViewModelFactory(application, repository)

        notesViewModel = ViewModelProvider(this,
            viewModelFactory)[NoteViewModel::class.java]
    }
}