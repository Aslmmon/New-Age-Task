package com.example.newagetask.features.add_bmi_details.data.datasource

import com.example.newagetask.features.add_bmi_details.data.model.PersonData

const val minimumWeight = 2
const val maximumWeight=100
const val minimumHeight=2
const val maximumHeight=250
class LocalDataSourceImplementation : DataSource {
    override fun getPersondata(): PersonData {
        return PersonData((minimumWeight..maximumWeight).toMutableList(), (minimumHeight..maximumHeight).toMutableList(), (0..2).toMutableList(),)
    }

}