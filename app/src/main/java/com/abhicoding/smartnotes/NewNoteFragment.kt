package com.abhicoding.smartnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.abhicoding.smartnotes.databinding.FragmentNewNoteBinding
import com.abhicoding.smartnotes.room.Note
import com.abhicoding.smartnotes.viewmodel.NoteViewModel

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {
    private var _binding : FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var mView: View
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).notesViewModel
        mView = view
    }

    private fun saveNote(view: View){
        val noteTitle = binding.noteTitle.text.toString().trim()
        val noteBody = binding.noteBody.text.toString().trim()

        if (noteTitle.isNotEmpty()){
            val note = Note(0,noteTitle,noteBody)

            notesViewModel.insertNote(note)
            Toast.makeText(mView.context,"Note saved successfully✅",Toast.LENGTH_SHORT).show()

            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)

        }else{
            Toast.makeText(mView.context,"⚠️ Please enter Note Title",Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onCreateOptionsMenu(menu, inflater)",
        "androidx.fragment.app.Fragment"
    )
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.hasVisibleItems()
        setHasOptionsMenu(true)
        inflater.inflate(R.menu.new_note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroy() {
        super.onDestroy()
        super.onDestroy()
        _binding = null
    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onOptionsItemSelected(item)",
        "androidx.fragment.app.Fragment"
    )
    )
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_save -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}