package com.example.newagetask.features.add_bmi_details.data.di

import com.example.newagetask.features.add_bmi_details.data.datasource.DataSource
import com.example.newagetask.features.add_bmi_details.data.datasource.LocalDataSourceImplementation
import com.example.newagetask.features.add_bmi_details.data.repoImpl.PersonDataRepositoryImplementation
import com.example.newagetask.features.add_bmi_details.domain.repository.PersonDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersonRepositoriesModule {

    @Provides
    fun bindLocalPersonDataSoruce(): DataSource {
        return LocalDataSourceImplementation()
    }
    @Provides
    fun bindPersonData(dataSource: DataSource): PersonDataRepository {
        return PersonDataRepositoryImplementation(dataSource)
    }



}