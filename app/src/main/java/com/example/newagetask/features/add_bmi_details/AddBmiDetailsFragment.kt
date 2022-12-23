package com.example.newagetask.features.add_bmi_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newagetask.R
import com.example.newagetask.features.add_bmi_details.adapter.BmiCreatorAdapter
import com.example.newagetask.features.add_bmi_details.adapter.PersonData
import com.google.android.material.button.MaterialButton


class AddBmiDetailsFragment : Fragment() {
    lateinit var bmiWeightCreatorAdapter: BmiCreatorAdapter
    lateinit var bmiHeightCreatorAdapter: BmiCreatorAdapter
    lateinit var bmiGenderCreatorAdapter: BmiCreatorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_bmi_details, container, false)
        bmiWeightCreatorAdapter = BmiCreatorAdapter()
        bmiHeightCreatorAdapter = BmiCreatorAdapter()
        bmiGenderCreatorAdapter = BmiCreatorAdapter()

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
        bmiWeightCreatorAdapter.differ.submitList(
            mutableListOf(
                PersonData("1"),
                PersonData("2"),
                PersonData("3"),
                PersonData("4"),
                PersonData("5"),

            )
        )

        bmiHeightCreatorAdapter.differ.submitList(
            mutableListOf(
                PersonData("1"),
                PersonData("2"),
                PersonData("3"),
                PersonData("4"),
                PersonData("5"),
            )
        )
        bmiGenderCreatorAdapter.differ.submitList(
            mutableListOf(
                PersonData("Male"),
                PersonData("Female"),
            )
        )
        view.findViewById<RecyclerView>(R.id.rv_weight).adapter = bmiWeightCreatorAdapter
        view.findViewById<RecyclerView>(R.id.rv_height).adapter = bmiHeightCreatorAdapter
        view.findViewById<RecyclerView>(R.id.rv_gender).adapter = bmiGenderCreatorAdapter
    }


}