package com.abhicoding.smartnotes

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.abhicoding.smartnotes.databinding.FragmentUpdateBinding
import com.abhicoding.smartnotes.room.Note
import com.abhicoding.smartnotes.viewmodel.NoteViewModel


class UpdateFragment : Fragment(R.layout.fragment_update) {
    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var currentNote : Note

    private val args : UpdateFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).notesViewModel
        currentNote = args.note!!

        binding.updateNoteTitle.setText(currentNote.noteTitle)
        binding.updateNoteBody.setText(currentNote.noteBody)

        // if user update the note

        binding.floatingActionButton.setOnClickListener {
            val title = binding.updateNoteTitle.text.toString()
            val body = binding.updateNoteBody.text.toString()
            if (title.isNotEmpty()){
                val note = Note(currentNote.id,title,body)
                notesViewModel.updateNote(note)
                Toast.makeText(view.context,"Note Updated Successfully ✅",Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_updateFragment_to_homeFragment)

            }else{
                Toast.makeText(context,"⚠️ Enter Note Title",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note Permanently?")
            setMessage("Are you sure to Delete the Note?")
            setIcon(R.drawable.delete)
            setPositiveButton("Delete"){_,_ ->
                notesViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(
                    R.id.action_updateFragment_to_homeFragment)

            }

            setNegativeButton("Cancel",null)
        }.create().show()
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.hasVisibleItems()
        setHasOptionsMenu(true)
        inflater.inflate(R.menu.menu_update,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}