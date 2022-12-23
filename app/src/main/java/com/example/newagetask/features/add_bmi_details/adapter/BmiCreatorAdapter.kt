package com.example.newagetask.features.add_bmi_details.adapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newagetask.R


const val header_layout_item=0
const val item_layout_item=1

//class BmiCreatorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (holder) {
//            is ViewHolderheaderLayout -> {
//                holder.setData()
//                holder.setIsRecyclable(false)
//            }
//            is ViewHolderItemLayout -> {
//                holder.setData(differ.currentList[position-1])
//                holder.setIsRecyclable(false)
//            }
//        }
//
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): RecyclerView.ViewHolder {
//        when (viewType) {
//            header_layout_item -> {
//                val view: View = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.bmi_item_header_layout, parent, false)
//                return ViewHolderheaderLayout(view)
//            }
//            item_layout_item -> {
//                val view: View = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.bmi_item_layout, parent, false)
//                return ViewHolderItemLayout(view)
//            }
//           else -> {
//               val view: View = LayoutInflater.from(parent.context)
//                   .inflate(R.layout.bmi_item_layout, parent, false)
//               return ViewHolderItemLayout(view)
//           }
//       }
//    }
//
//    override fun getItemCount() = differ.currentList.size +1
//
//    inner class ViewHolderItemLayout(var view: View) : RecyclerView.ViewHolder(view) {
//        fun setData(item: orderItem) {
//            view.findViewById<TextView>(R.id.tv_weight).text = item.id.toString()
////            view.findViewById<TextView>(R.id.tv_height).text = item.id.toString()
////            view.findViewById<TextView>(R.id.tv_gender).text = item.id.toString()
//
//        }
//
//    }
//
//    inner class ViewHolderheaderLayout(var view: View) : RecyclerView.ViewHolder(view) {
//        fun setData() {
//            view.findViewById<TextView>(R.id.tv_weight_title).text = view.resources.getString(R.string.weightTitle)
//            view.findViewById<TextView>(R.id.tv_height_title).text = view.resources.getString(R.string.heightTitle)
//            view.findViewById<TextView>(R.id.tv_gender_title).text = view.resources.getString(R.string.genderTitle)
//
//        }
//
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        if (isPositionHeader(position))
//            return header_layout_item
//        return item_layout_item
//    }
//
//    private fun isPositionHeader(position: Int): Boolean {
//        return position == 0
//    }
//    private val differCallback = object : DiffUtil.ItemCallback<orderItem>() {
//        override fun areItemsTheSame(oldItem: orderItem, newItem: orderItem): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: orderItem, newItem: orderItem): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//    val differ = AsyncListDiffer(this, differCallback)
//
//}
data class orderItem(var id:Int)



class BmiWeightCreatorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectedItem = -1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderItemLayout).setData(differ.currentList[position])
        holder.setIsRecyclable(false)
        if (position == selectedItem) {
//            (holder as ViewHolderItemLayout).view.findViewById<TextView>(R.id.tv_weight).setTextColor(
//                Color.parseColor("#094673"));
        } else {
//            (holder as ViewHolderItemLayout).view.findViewById<TextView>(R.id.tv_weight).setTextColor(
//                Color.GRAY)
        }
    }
    fun setSelecteditem(selecteditem: Int) {
        selectedItem = selecteditem
        //notifyDataSetChanged()
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
        fun setData(item: orderItem) {
            view.findViewById<TextView>(R.id.tv_weight).text = item.id.toString()
//            view.findViewById<TextView>(R.id.tv_height).text = item.id.toString()
//            view.findViewById<TextView>(R.id.tv_gender).text = item.id.toString()

        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<orderItem>() {
        override fun areItemsTheSame(oldItem: orderItem, newItem: orderItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: orderItem, newItem: orderItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)


}
