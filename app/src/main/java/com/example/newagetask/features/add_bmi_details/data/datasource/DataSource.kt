package com.example.newagetask.features.add_bmi_details.data.datasource

import com.example.newagetask.features.add_bmi_details.data.model.PersonData
import com.example.newagetask.features.add_bmi_details.data.model.PersonProfile
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData

interface DataSource {
    fun getPersondata():PersonData

    fun calculateBMI(personProfile: PersonProfile) : PersonResultData

}