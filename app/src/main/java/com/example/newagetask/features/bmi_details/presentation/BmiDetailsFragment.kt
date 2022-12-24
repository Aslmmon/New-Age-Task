package com.example.newagetask.features.bmi_details.presentation

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.newagetask.R
import com.example.newagetask.common.CustomCompoundButtons
import com.example.newagetask.common.navigateToGooglePlay
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BmiDetailsFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var bmiResultTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bmi_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bmiResultTextView = view.findViewById<TextView>(R.id.tv_bmi_result)
        view.findViewById<CustomCompoundButtons>(R.id.custom_rate_button).setOnClickListener {
            requireActivity().navigateToGooglePlay()
        }
        view.findViewById<CustomCompoundButtons>(R.id.custom_share_button).setOnClickListener {
        }
        sharedViewModel.personResultData.observe(requireActivity(), Observer {
            bindDataToViews(it, view)
        })

    }

    private fun bindDataToViews(it: PersonResultData?, view: View) {
        bmiResultTextView.text = processStringtoBeWithDifferentFont(it)

        view.findViewById<TextView>(R.id.tv_bmi_result_description).text = String.format(
            getString(R.string.welcome_message),
            it?.personName ?: "",
            it?.bmiResultStatus
        )
        view.findViewById<TextView>(R.id.normal_results_description).text =
            String.format(getString(R.string.normal), it?.panderalIndexResult ?: "")

    }

    private fun processStringtoBeWithDifferentFont(it: PersonResultData?): SpannableString {
        val ss1 = SpannableString(it?.bmiResult)
        ss1.setSpan(
            RelativeSizeSpan(0.5f),
            it?.bmiResult?.indexOf(".") ?: 2,
            ss1.length,
            0
        ) // set size
        ss1.setSpan(
            ForegroundColorSpan(Color.WHITE),
            it?.bmiResult?.indexOf(".") ?: 2,
            ss1.length,
            0
        ) // set color
        return ss1
    }


}