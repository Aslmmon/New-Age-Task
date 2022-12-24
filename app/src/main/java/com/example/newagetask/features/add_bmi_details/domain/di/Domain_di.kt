package com.example.newagetask.features.add_bmi_details.domain.di

import com.example.newagetask.features.add_bmi_details.domain.repository.PersonDataRepository
import com.example.newagetask.features.add_bmi_details.domain.usecase.GetPersonDataUseCase
import com.example.newagetask.features.add_bmi_details.domain.usecase.GetPersonDataUseCaseImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class Domain_di {

    @Provides
    fun bindGetPersonsData(persoDataRepo: PersonDataRepository): GetPersonDataUseCase {
        return GetPersonDataUseCaseImplementation(persoDataRepo)
    }


}
