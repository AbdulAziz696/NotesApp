package com.aziz.notesapp

import androidx.lifecycle.LiveData
import com.aziz.notesapp.Data.model.NoteData
import com.aziz.notesapp.Data.repository.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val getAllData : LiveData<List<NoteData>> = noteDao.getDataAll()
    val shortByHighPriority : LiveData<List<NoteData>> = noteDao.shortByHighPriority()
    val shortByLowPriority : LiveData<List<NoteData>> = noteDao.shortByLowPriority()

    fun insertData(noteData: NoteData) {
        noteDao.insertData(noteData)
    }
    fun updateDate(noteData: NoteData) {
        noteDao.updatedata(noteData)
    }
    fun deleteData(noteData: NoteData) {
        noteDao.deleteData(noteData)
    }
    fun deleteAllDAta() {
        noteDao.deleteAllData()
    }
    fun searchQuery(searchQuery: String) : LiveData<List<NoteData>>{
        return noteDao.seachDatabase(searchQuery)
    }
}