package com.example.newagetask.features.bmi_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData

class SharedViewModel : ViewModel() {

    private val _personResultData = MutableLiveData<PersonResultData>()
    val personResultData: LiveData<PersonResultData> = _personResultData

    fun setPersonData(personResultData: PersonResultData){
         _personResultData.value = personResultData
     }
}