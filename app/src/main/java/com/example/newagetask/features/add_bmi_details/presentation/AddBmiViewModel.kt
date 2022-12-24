package com.example.newagetask.features.add_bmi_details.presentation

import androidx.lifecycle.ViewModel
import com.example.newagetask.features.add_bmi_details.domain.usecase.GetPersonDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class AddBmiViewModel @Inject constructor(
    private val updateProductUseCase: GetPersonDataUseCase,
    ) : ViewModel()
{
}