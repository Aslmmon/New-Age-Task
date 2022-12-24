package com.example.newagetask.features.add_bmi_details.domain.usecase

import com.example.newagetask.features.add_bmi_details.data.model.PersonData
import com.example.newagetask.features.add_bmi_details.domain.repository.PersonDataRepository
import javax.inject.Inject


interface GetPersonDataUseCase {
     fun getPersonData(): PersonData
}

class GetPersonDataUseCaseImplementation @Inject constructor(var repository:PersonDataRepository ) :
    GetPersonDataUseCase {
    override fun getPersonData() = repository.getPersonData()

}
