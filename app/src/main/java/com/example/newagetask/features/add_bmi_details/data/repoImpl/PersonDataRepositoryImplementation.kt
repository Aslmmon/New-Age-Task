package com.example.newagetask.features.add_bmi_details.data.repoImpl

import com.example.newagetask.features.add_bmi_details.data.datasource.DataSource
import com.example.newagetask.features.add_bmi_details.data.model.PersonProfile
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData
import com.example.newagetask.features.add_bmi_details.domain.repository.PersonDataRepository

class PersonDataRepositoryImplementation(var localDataSource:DataSource) : PersonDataRepository {
    override fun getPersonData() = localDataSource.getPersondata()
    override fun calculateBMI(personProfile: PersonProfile) = localDataSource.calculateBMI(personProfile)
}