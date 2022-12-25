package com.example.newagetask.features.add_bmi_details.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newagetask.R


class BmiCreatorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selected_position = 0 // You have to set this globally in the Adapter class


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderItemLayout).setData(differ.currentList[position])
        holder.setIsRecyclable(false)
        if (selected_position == position) {
            changeSelectedItemFont(holder)
        } else
            returnNormalItem(holder)

        holder.itemView.setOnClickListener {
            setSelectedItem(position)
        }

    }

    private fun returnNormalItem(holder: RecyclerView.ViewHolder) {
        holder.itemView.findViewById<TextView>(R.id.tv_item_text)
            .setTextColor(holder.itemView.resources.getColor(R.color.gray_color))
        holder.itemView.findViewById<View>(R.id.view).visibility = View.GONE
    }

    private fun changeSelectedItemFont(holder: RecyclerView.ViewHolder) {
        val typeface = ResourcesCompat.getFont(holder.itemView.context, R.font.poppins_bold_700)
        holder.itemView.findViewById<TextView>(R.id.tv_item_text).typeface = typeface
        holder.itemView.findViewById<TextView>(R.id.tv_item_text)
            .setTextColor(holder.itemView.resources.getColor(R.color.primary))
        holder.itemView.findViewById<View>(R.id.view).visibility = View.VISIBLE
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSelectedItem(newPosition: Int) {
        selected_position = newPosition
        notifyDataSetChanged()
    }

    fun getItemSelected() = differ.currentList[selected_position]

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderItemLayout {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.bmi_single_item, parent, false)
        return ViewHolderItemLayout(view)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolderItemLayout(var view: View) : RecyclerView.ViewHolder(view) {
        fun setData(item: String) {
            view.findViewById<TextView>(R.id.tv_item_text).text = item

        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)


}

