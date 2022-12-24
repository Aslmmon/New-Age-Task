package com.example.newagetask.features.add_bmi_details.domain.repository

import com.example.newagetask.features.add_bmi_details.data.model.PersonData

interface PersonDataRepository {
    fun getPersonData() : PersonData
}