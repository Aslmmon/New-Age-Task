package com.example.newagetask.features.add_bmi_details.data.datasource

import com.example.newagetask.common.roundOffTwoDecimalPoints
import com.example.newagetask.features.add_bmi_details.data.model.PersonData
import com.example.newagetask.features.add_bmi_details.data.model.PersonProfile
import com.example.newagetask.features.add_bmi_details.data.model.PersonResultData

const val minimumWeight = 20
const val maximumWeight = 150
const val minimumHeight = 50
const val maximumHeight = 200

class LocalDataSourceImplementation : DataSource {
    override fun getPersondata(): PersonData {
        return PersonData(
            (minimumWeight..maximumWeight).map { it->it.toString() }.toMutableList(),
            (minimumHeight..maximumHeight).map { it->it.toString() }.toMutableList(),
            mutableListOf("Male","Female"),
        )
    }

    override fun calculateBMI(personProfile: PersonProfile): PersonResultData {
        val bmiResult = calculateBodyMassIndex(personProfile)
        val bmiStatus = getBMIStatus(bmiResult = bmiResult)
        val panderalIndex = getPanderalIndex(personProfile)

        return PersonResultData(
            bmiResult = bmiResult.roundOffTwoDecimalPoints().toString(),
            bmiResultStatus = bmiStatus,
            panderalIndexResult = panderalIndex.roundOffTwoDecimalPoints().toString(),
            personName = personProfile.personName
        )
    }

    private fun calculateBodyMassIndex(personProfile: PersonProfile) =
        (personProfile.weight.div(personProfile.height * personProfile.height)) * 10000

    private fun getBMIStatus(bmiResult: Double): String {
        return when (true) {
            (bmiResult < 18.5) -> BMI_WEIGHT_STATUS.Underweight.name
            (bmiResult > 18.5 && bmiResult < 24.9) -> BMI_WEIGHT_STATUS.Healthy.name
            (bmiResult > 25.0 && bmiResult < 29.9) -> BMI_WEIGHT_STATUS.Overweight.name
            (bmiResult > 30.0) -> BMI_WEIGHT_STATUS.Obesity.name

            else -> BMI_WEIGHT_STATUS.Undefined.name
        }
    }

    private fun getPanderalIndex(personProfile: PersonProfile): Double {
        val ponderalIndex: Double = personProfile.weight.div(Math.cbrt(personProfile.height))
        return ponderalIndex

    }


}

enum class BMI_WEIGHT_STATUS {
    Underweight, Healthy, Overweight, Obesity, Undefined
}