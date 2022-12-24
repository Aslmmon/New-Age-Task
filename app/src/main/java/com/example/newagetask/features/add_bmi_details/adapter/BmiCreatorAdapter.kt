package com.example.newagetask.features.add_bmi_details.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newagetask.R


class BmiCreatorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var selected_position = 0 // You have to set this globally in the Adapter class


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderItemLayout).setData(differ.currentList[position])
        holder.setIsRecyclable(false)
        if (selected_position == position){
            holder.itemView.findViewById<TextView>(R.id.tv_item_text).setTypeface(null, Typeface.BOLD);
            holder.itemView.findViewById<TextView>(R.id.tv_item_text).setTextColor(holder.itemView.resources.getColor(R.color.primary))
            holder.itemView.findViewById<View>(R.id.view).visibility = View.VISIBLE

        }else{
            holder.itemView.findViewById<TextView>(R.id.tv_item_text).setTextColor(holder.itemView.resources.getColor(R.color.gray_color))
            holder.itemView.findViewById<View>(R.id.view).visibility = View.GONE

        }

    }
    fun setSelectedItem(newPosition: Int){
        selected_position = newPosition
        notifyDataSetChanged()
    }
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
        fun setData(item: PersonData) {
            view.findViewById<TextView>(R.id.tv_item_text).text = item.id.toString()
        }
    }
    private val differCallback = object : DiffUtil.ItemCallback<PersonData>() {
        override fun areItemsTheSame(oldItem: PersonData, newItem: PersonData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PersonData, newItem: PersonData): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)


}
data class PersonData(var id:String)
