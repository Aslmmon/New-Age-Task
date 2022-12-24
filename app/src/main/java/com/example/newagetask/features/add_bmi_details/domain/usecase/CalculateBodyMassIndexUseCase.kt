package com.example.newagetask.features.add_bmi_details.domain.usecase

import com.example.newagetask.features.add_bmi_details.data.model.PersonData
import com.example.newagetask.features.add_bmi_details.data.model.PersonProfile
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData
import com.example.newagetask.features.add_bmi_details.domain.repository.PersonDataRepository
import javax.inject.Inject



interface CalculateBodyMassIndexUseCase {
    fun calculateBMIforPersonse(personProfile: PersonProfile): PersonResultData
}

class CalculateBodyMassIndexUseCaseImplementation @Inject constructor(var repository: PersonDataRepository) :
    CalculateBodyMassIndexUseCase {
    override fun calculateBMIforPersonse(personProfile: PersonProfile)= repository.calculateBMI(personProfile)
}
