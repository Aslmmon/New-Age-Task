package com.example.newagetask.features.add_bmi_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.newagetask.MainActivity
import com.example.newagetask.R
import com.example.newagetask.common.base.BaseActivity
import com.example.newagetask.features.add_bmi_details.data.model.PersonData
import com.example.newagetask.features.add_bmi_details.data.model.PersonProfile
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData
import com.example.newagetask.features.add_bmi_details.presentation.adapter.BmiCreatorAdapter
import com.example.newagetask.features.add_bmi_details.presentation.viewModel.AddBmiViewModel
import com.example.newagetask.features.add_bmi_details.presentation.viewModel.Status
import com.example.newagetask.features.bmi_details.presentation.SharedViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddBmiDetailsFragment : Fragment() {
    var bmiWeightCreatorAdapter = BmiCreatorAdapter()
    var bmiHeightCreatorAdapter = BmiCreatorAdapter()
    var bmiGenderCreatorAdapter = BmiCreatorAdapter()
    var list = mutableListOf<PersonData>()
    private val addBmiViewModel: AddBmiViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var userNameEditText: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_bmi_details, container, false)
        userNameEditText = view.findViewById(R.id.ed_name_user)

        addBmiViewModel.getPersonsData().observe(requireActivity(), Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    submitDataToAdapter(it.data)
                }
                Status.ERROR -> {}
                Status.LOADING -> {}
            }
        })

        return view.rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBmiCreatorData(view)
        changeToolbarTitle()
        (requireActivity() as MainActivity).isBackButtonVisible(false)



        view.findViewById<MaterialButton>(R.id.btn_calculate).setOnClickListener {
            val triple = Triple(
                bmiWeightCreatorAdapter.getItemSelected(),
                bmiHeightCreatorAdapter.getItemSelected(),
                bmiGenderCreatorAdapter.getItemSelected()
            )
            addBmiViewModel.calculatePersonBMI(
                PersonProfile(
                    weight = triple.first.toDouble(),
                    height = triple.second.toDouble(),
                    gender = triple.third,
                    userNameEditText.text.toString()
                )
            ).observe(requireActivity(), Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        sharedViewModel.setPersonData(
                            personResultData = it.data ?: PersonResultData()
                        )
                        (requireActivity() as BaseActivity).showInterstitialAdd()
                        findNavController().navigate(R.id.goToBmiDetailsFragment)
                    }
                    Status.ERROR -> {}
                    Status.LOADING -> {}
                }
            })

        }
    }

    private fun changeToolbarTitle() =
        (requireActivity() as MainActivity).changeToolbarName(resources.getString(R.string.add_bmi_details))


    private fun submitDataToAdapter(personData: PersonData?) {
        bmiWeightCreatorAdapter.differ.submitList(personData?.weightList)
        bmiHeightCreatorAdapter.differ.submitList(personData?.heightList)
        bmiGenderCreatorAdapter.differ.submitList(personData?.genederList)
    }

    private fun initBmiCreatorData(view: View) {
        val weightRecyclerView = view.findViewById<RecyclerView>(R.id.rv_weight)
        val heightRecyclerView = view.findViewById<RecyclerView>(R.id.rv_height)
        val genderRecyclerView = view.findViewById<RecyclerView>(R.id.rv_gender)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(weightRecyclerView)
        snapHelper.attachToRecyclerView(heightRecyclerView)
        snapHelper.attachToRecyclerView(genderRecyclerView)

        weightRecyclerView.adapter = bmiWeightCreatorAdapter
        heightRecyclerView.adapter = bmiHeightCreatorAdapter
        genderRecyclerView.adapter = bmiGenderCreatorAdapter



        setSelectionToRecyclerView(weightRecyclerView, bmiWeightCreatorAdapter)
        setSelectionToRecyclerView(heightRecyclerView, bmiHeightCreatorAdapter)
        setSelectionToRecyclerView(genderRecyclerView, bmiGenderCreatorAdapter)

    }

    private fun setSelectionToRecyclerView(recyclerView: RecyclerView, adapter: BmiCreatorAdapter) {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(
            MiddleItemFinder(
                requireContext(), layoutManager, list, adapter
            )
        )
    }

}


