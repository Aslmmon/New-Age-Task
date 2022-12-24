package com.example.newagetask.features.add_bmi_details.domain.repository

import com.example.newagetask.features.add_bmi_details.data.model.PersonData
import com.example.newagetask.features.add_bmi_details.data.model.PersonProfile
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData

interface PersonDataRepository {
    fun getPersonData() : PersonData
    fun calculateBMI(personProfile: PersonProfile) : PersonResultData
}