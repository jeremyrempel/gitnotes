package com.github.jeremyrempel.gitnotes.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.jeremyrempel.gitnotes.android.ListAdapter.MyViewHolder
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import kotlinx.android.synthetic.main.row_list.view.*

class ListAdapter(val callback: (ContentsResponse) -> Unit) :
    ListAdapter<ContentsResponse, MyViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(getItem(position), callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(row: ContentsResponse, callback: (ContentsResponse) -> Unit) {
            view.text1.text = row.name
            view.setOnClickListener { callback.invoke(row) }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<ContentsResponse>() {
        override fun areItemsTheSame(oldItem: ContentsResponse, newItem: ContentsResponse): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: ContentsResponse, newItem: ContentsResponse): Boolean =
            oldItem == newItem
    }
}