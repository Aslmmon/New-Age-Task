package com.example.newagetask.features.add_bmi_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newagetask.R
import com.example.newagetask.features.add_bmi_details.adapter.BmiCreatorAdapter
import com.example.newagetask.features.add_bmi_details.adapter.PersonData
import com.google.android.material.button.MaterialButton


class AddBmiDetailsFragment : Fragment() {
    lateinit var bmiWeightCreatorAdapter: BmiCreatorAdapter
    lateinit var bmiHeightCreatorAdapter: BmiCreatorAdapter
    lateinit var bmiGenderCreatorAdapter: BmiCreatorAdapter
     var list = mutableListOf<PersonData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_bmi_details, container, false)

        Toast.makeText(requireContext(), getDpFromPx(70).toString(), Toast.LENGTH_SHORT).show()
        bmiWeightCreatorAdapter = BmiCreatorAdapter()
        bmiHeightCreatorAdapter = BmiCreatorAdapter()
        bmiGenderCreatorAdapter = BmiCreatorAdapter()
        list = mutableListOf(
            PersonData("1"),
            PersonData("2"),
            PersonData("3"),
            PersonData("4"),
            PersonData("5"),
            PersonData("6"),
            PersonData("7"),
            PersonData("8"),
            PersonData("9"),
            PersonData("10"),

            )

        return view.rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBmiCreatorData(view)

        view.findViewById<MaterialButton>(R.id.btn_calculate).setOnClickListener {
            findNavController().navigate(R.id.goToBmiDetailsFragment)
        }
    }

    private fun initBmiCreatorData(view: View) {
        val weightRecyclerView = view.findViewById<RecyclerView>(R.id.rv_weight)
        val heightRecyclerView = view.findViewById<RecyclerView>(R.id.rv_height)
        val genderRecyclerView = view.findViewById<RecyclerView>(R.id.rv_gender)
        weightRecyclerView.adapter = bmiWeightCreatorAdapter
        heightRecyclerView.adapter = bmiHeightCreatorAdapter
       genderRecyclerView.adapter = bmiGenderCreatorAdapter

        bmiWeightCreatorAdapter.differ.submitList(list)

        bmiHeightCreatorAdapter.differ.submitList(list)
        bmiGenderCreatorAdapter.differ.submitList(
            mutableListOf(
                PersonData("Male"),
                PersonData("Female"),
            )
        )

        setSelectionToRecyclerView(view,weightRecyclerView,bmiWeightCreatorAdapter)
        setSelectionToRecyclerView(view,heightRecyclerView,bmiHeightCreatorAdapter)
        setSelectionToRecyclerView(view,genderRecyclerView,bmiGenderCreatorAdapter)

    }

    private fun setSelectionToRecyclerView(view: View,recyclerView: RecyclerView, adapter: BmiCreatorAdapter) {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(
            MiddleItemFinder(
                requireContext(), layoutManager,list, adapter
            )
        )
    }

    fun getDpFromPx(px:Int): Int {
       val displayMetrics = context?.resources?.displayMetrics;
        return  ((px/ displayMetrics?.density!!)+0.5).toInt()
    }

}



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