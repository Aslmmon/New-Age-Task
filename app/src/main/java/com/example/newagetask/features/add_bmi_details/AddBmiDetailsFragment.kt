package com.example.newagetask.features.add_bmi_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newagetask.R
import com.example.newagetask.features.add_bmi_details.adapter.BmiWeightCreatorAdapter
import com.example.newagetask.features.add_bmi_details.adapter.orderItem
import com.google.android.material.button.MaterialButton


class AddBmiDetailsFragment : Fragment() {
    lateinit var bmiWeightCreatorAdapter: BmiWeightCreatorAdapter
    lateinit var bmiHeightCreatorAdapter: BmiWeightCreatorAdapter
    lateinit var bmiGenderCreatorAdapter: BmiWeightCreatorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_bmi_details, container, false)
        bmiWeightCreatorAdapter = BmiWeightCreatorAdapter()
        bmiHeightCreatorAdapter = BmiWeightCreatorAdapter()
        bmiGenderCreatorAdapter = BmiWeightCreatorAdapter()

        return view.rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bmiWeightCreatorAdapter.differ.submitList(mutableListOf(orderItem(1),
            orderItem(2),orderItem(3),orderItem(4),orderItem(5),orderItem(6),orderItem(7),orderItem(8),orderItem(9)
        ))

        bmiHeightCreatorAdapter.differ.submitList(mutableListOf(orderItem(1),
            orderItem(2),orderItem(3),orderItem(4),orderItem(5),orderItem(6),orderItem(7),orderItem(8),orderItem(9)
        ))
        bmiGenderCreatorAdapter.differ.submitList(mutableListOf(orderItem(1),
            orderItem(2),orderItem(3),orderItem(4),orderItem(5),orderItem(6),orderItem(7),orderItem(8),orderItem(9)
        ))

        view.findViewById<RecyclerView>(R.id.rv_height).adapter = bmiHeightCreatorAdapter
        view.findViewById<RecyclerView>(R.id.rv_gender).adapter = bmiGenderCreatorAdapter

        view.findViewById<MaterialButton>(R.id.btn_calculate).setOnClickListener {
            findNavController().navigate(R.id.goToBmiDetailsFragment)
        }
    }


}