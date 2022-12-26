package com.example.newagetask.features.add_bmi_details.data.datasource

import android.util.Log
import com.example.newagetask.common.getCubicSquare
import com.example.newagetask.common.getHeightMeter
import com.example.newagetask.common.getMultipleSquare
import com.example.newagetask.common.roundOffTwoDecimalPoints
import com.example.newagetask.features.add_bmi_details.data.model.PersonData
import com.example.newagetask.features.add_bmi_details.data.model.PersonProfile
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData

const val minimumWeight = 20
const val maximumWeight = 150
const val minimumHeight = 50
const val maximumHeight = 200
const val male_gender = "Male"
const val female_gender = "Female"

class LocalDataSourceImplementation : DataSource {
    override fun getPersondata(): PersonData {
        return PersonData(
            (minimumWeight..maximumWeight).map { it -> it.toString() }.toMutableList(),
            (minimumHeight..maximumHeight).map { it -> it.toString() }.toMutableList(),
            mutableListOf(male_gender, female_gender),
        )
    }

    override fun calculateBMI(personProfile: PersonProfile): PersonResultData {
        val bmiResult = calculateBodyMassIndex(personProfile)
        val bmiStatus = getBMIStatus(bmiResult = bmiResult)
        val panderalIndex = getPandoralIndex(personProfile)

        return PersonResultData(
            bmiResult = bmiResult.roundOffTwoDecimalPoints().toString(),
            bmiResultStatus = bmiStatus,
            panderalIndexResult = panderalIndex.roundOffTwoDecimalPoints().toString(),
            personName = personProfile.personName
        )
    }

    private fun calculateBodyMassIndex(personProfile: PersonProfile) =
        (personProfile.weight.div(personProfile.height.getHeightMeter().getMultipleSquare()))

    private fun getBMIStatus(bmiResult: Double): String {
        return when (true) {
            (bmiResult < 18.5) -> BMI_WEIGHT_STATUS.Underweight.name
            (bmiResult > 18.5 && bmiResult < 24.9) -> BMI_WEIGHT_STATUS.Healthy.name
            (bmiResult > 25.0 && bmiResult < 29.9) -> BMI_WEIGHT_STATUS.Overweight.name
            (bmiResult > 30.0) -> BMI_WEIGHT_STATUS.Obesity.name

            else -> BMI_WEIGHT_STATUS.Undefined.name
        }
    }

    private fun getPandoralIndex(personProfile: PersonProfile): Double {
        val heightResult =
            (personProfile.height.getHeightMeter().getCubicSquare())
        return (personProfile.weight.div(heightResult))

    }


}

enum class BMI_WEIGHT_STATUS {
    Underweight, Healthy, Overweight, Obesity, Undefined
}