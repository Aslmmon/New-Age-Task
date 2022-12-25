package com.example.newagetask.features.add_bmi_details.presentation.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.newagetask.features.add_bmi_details.data.model.PersonProfile
import com.example.newagetask.features.add_bmi_details.domain.usecase.CalculateBodyMassIndexUseCase
import com.example.newagetask.features.add_bmi_details.domain.usecase.GetPersonDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AddBmiViewModel @Inject constructor(
    private val getPersonsDataUseCase: GetPersonDataUseCase,
    private val calculateBodyMassIndexUseCase: CalculateBodyMassIndexUseCase,

    ) : ViewModel()
{


    fun getPersonsData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = getPersonsDataUseCase.getPersonData()
            emit(Resource.success(data = result))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    exception.message.toString()
                )
            )
        }
    }

    fun calculatePersonBMI(personProfile: PersonProfile) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = calculateBodyMassIndexUseCase.calculateBMIforPersons(personProfile)
            emit(Resource.success(data = result))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    exception.message.toString()
                )
            )
        }
    }

}


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null)

        @SuppressLint("LogNotTimber")
        fun <T> error(erorr: String): Resource<T> {
            return Resource(status = Status.ERROR, data = null, message = erorr)
        }

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
