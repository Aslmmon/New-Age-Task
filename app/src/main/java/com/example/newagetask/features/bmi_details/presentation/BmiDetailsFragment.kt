package com.example.newagetask.features.bmi_details.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.newagetask.R
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BmiDetailsFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bmi_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.personResultData.observe(requireActivity(), Observer {
            bindDataToViews(it,view)
        })

    }

    private fun bindDataToViews(it: PersonResultData?, view: View) {
       view.findViewById<TextView>(R.id.tv_bmi_result).text = it?.bmiResult
        view.findViewById<TextView>(R.id.tv_bmi_result_description).text="hello ${it?.bmiResult} you are ${it?.panderalIndexResult}"
    }
}