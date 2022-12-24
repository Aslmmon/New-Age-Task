package com.example.newagetask.features.add_bmi_details.presentation

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newagetask.features.add_bmi_details.data.model.PersonData
import com.example.newagetask.features.add_bmi_details.presentation.adapter.BmiCreatorAdapter


class MiddleItemFinder(
    context: Context,
    layoutManager: LinearLayoutManager,
    var list: MutableList<PersonData>,
    var adapter: BmiCreatorAdapter
) :
    RecyclerView.OnScrollListener() {
    private val context: Context
    private val layoutManager: LinearLayoutManager
    init {
        this.context = context
        this.layoutManager = layoutManager
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        val firstItem: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
        val lastItem: Int = layoutManager.findLastCompletelyVisibleItemPosition()

        if (list.size == 1) {
            adapter.setSelectedItem(0)
        } else if (lastItem == list.size - 1) {
            adapter.setSelectedItem(list.size -2)
        } else {
            adapter.setSelectedItem(firstItem+1)
        }
    }
}