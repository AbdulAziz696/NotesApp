package com.aziz.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aziz.notesapp.Data.model.NoteData
import com.aziz.notesapp.databinding.RowLayoutItemBinding

class Listadapter: RecyclerView.Adapter<Listadapter.ViewHolder>() {
    var dataList = emptyList<NoteData>()
    class ViewHolder(val binding: RowLayoutItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(noteData: NoteData) {
            binding.noteData = noteData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(noteData: List<NoteData>) {
        val noteDiffUtil = NoteDiffUtil(dataList, noteData)
        val noteDiffResult = DiffUtil.calculateDiff(noteDiffUtil)
        this.dataList = noteData
        noteDiffResult.dispatchUpdatesTo(this)
    }
}