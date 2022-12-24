package com.example.newagetask.features.add_bmi_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newagetask.R
import com.example.newagetask.common.base.BaseActivity
import com.example.newagetask.features.add_bmi_details.data.model.PersonData
import com.example.newagetask.features.add_bmi_details.data.model.PersonProfile
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData
import com.example.newagetask.features.add_bmi_details.presentation.adapter.BmiCreatorAdapter
import com.example.newagetask.features.bmi_details.presentation.SharedViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddBmiDetailsFragment : Fragment() {
    lateinit var bmiWeightCreatorAdapter: BmiCreatorAdapter
    lateinit var bmiHeightCreatorAdapter: BmiCreatorAdapter
    lateinit var bmiGenderCreatorAdapter: BmiCreatorAdapter
     var list = mutableListOf<PersonData>()
    private val addBmiViewModel: AddBmiViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()



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

        addBmiViewModel.getPersonsData().observe(requireActivity(), Observer {
            when(it.status){
                Status.SUCCESS->{
                    submitDataToAdapter(it.data)
                }
                Status.ERROR ->{}
                Status.LOADING ->{}
            }
        })

        view.findViewById<MaterialButton>(R.id.btn_calculate).setOnClickListener {
            addBmiViewModel.calculatePersonBMI(PersonProfile(20,20,1)).observe(requireActivity(), Observer {
                when(it.status){
                    Status.SUCCESS->{
                        sharedViewModel.setPersonData(personResultData = it.data ?: PersonResultData())
                        (requireActivity() as BaseActivity).showInterstitialAdd()
                        findNavController().navigate(R.id.goToBmiDetailsFragment)
                    }
                    Status.ERROR ->{}
                    Status.LOADING ->{}
                }
            })

        }
    }

    private fun submitDataToAdapter(personData: PersonData?) {
        bmiWeightCreatorAdapter.differ.submitList(personData?.weightList)
        bmiHeightCreatorAdapter.differ.submitList(personData?.heightList)
        bmiGenderCreatorAdapter.differ.submitList(personData?.genederList)
    }

    private fun initBmiCreatorData(view: View) {
        val weightRecyclerView = view.findViewById<RecyclerView>(R.id.rv_weight)
        val heightRecyclerView = view.findViewById<RecyclerView>(R.id.rv_height)
        val genderRecyclerView = view.findViewById<RecyclerView>(R.id.rv_gender)
        weightRecyclerView.adapter = bmiWeightCreatorAdapter
        heightRecyclerView.adapter = bmiHeightCreatorAdapter
       genderRecyclerView.adapter = bmiGenderCreatorAdapter



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

}


