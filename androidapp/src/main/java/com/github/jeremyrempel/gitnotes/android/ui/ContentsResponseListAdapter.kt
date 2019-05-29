package com.github.jeremyrempel.gitnotes.android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.jeremyrempel.gitnotes.android.R
import com.github.jeremyrempel.gitnotes.android.ui.ContentsResponseListAdapter.MyViewHolder
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import kotlinx.android.synthetic.main.row_list.view.*

class ContentsResponseListAdapter(private val callback: (ContentsResponse) -> Unit) :
    ListAdapter<ContentsResponse, MyViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(getItem(position), callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(row: ContentsResponse, callback: (ContentsResponse) -> Unit) {
            view.text_title.text = row.name

            if (row.size == 0L) {
                view.text_size.isGone = true
            } else {
                view.text_size.isGone = false
                view.text_size.text = "${row.size} bytes"
            }

            val icon = when (row.type) {
                "file" -> R.drawable.ic_content_paste_black_24dp
                "dir" -> R.drawable.ic_folder_black_24dp
                else -> R.drawable.ic_error_outline_black_24dp
            }

            val iconDrawable = ResourcesCompat.getDrawable(view.context.resources, icon, null)
            view.row_image.setImageDrawable(iconDrawable)

            view.setOnClickListener { callback.invoke(row) }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<ContentsResponse>() {
        override fun areItemsTheSame(oldItem: ContentsResponse, newItem: ContentsResponse): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: ContentsResponse, newItem: ContentsResponse): Boolean =
            oldItem == newItem
    }
}