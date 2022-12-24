package com.example.newagetask.features.add_bmi_details.data.datasource

import com.example.newagetask.features.add_bmi_details.data.model.PersonData

interface DataSource {
    fun getPersondata():PersonData
}