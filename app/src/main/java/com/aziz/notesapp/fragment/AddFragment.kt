package com.aziz.notesapp.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.aziz.notesapp.Data.model.NoteData
import com.aziz.notesapp.NotesViewModel
import com.aziz.notesapp.R
import com.aziz.notesapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {


    private var _addbinding: FragmentAddBinding? = null
    private val addBinding get() = _addbinding!!

    private val noteViewModel: NotesViewModel by viewModels()
    private val sharedViewModel: SharedViewModels by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _addbinding = FragmentAddBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        addBinding.spPrioritas.onItemSelectedListener = sharedViewModel.listener

        return addBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        membuat menu ketika icon add di klik dia akan ngapain
        if (item.itemId == R.id.menu_add) {
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
//        membuat fun masukin data ke db, nah pertama buat dulu var dari view yang dibutuhkan
        val mTitle = addBinding.etTitle
        val mPriority = addBinding.spPrioritas.selectedItem.toString()
        val mDesc = addBinding.etDesc.text.toString()

        val validation = sharedViewModel.verifyDataForumUser(mPriority, mDesc)
        if (validation) {
            val newData = NoteData(
                0,
                mPriority,
                sharedViewModel.parsePriority(mPriority),
                mDesc
            )
            noteViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Data masih kosong", Toast.LENGTH_SHORT).show()
        }
    }
}