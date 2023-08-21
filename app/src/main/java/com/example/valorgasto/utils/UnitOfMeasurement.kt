package com.example.valorgasto.utils

import androidx.annotation.StringRes
import com.example.valorgasto.R

enum class UnitOfMeasurement {
    Kg,
    G,
    Mg,
    Unit,
    L,
    Ml;

    @StringRes
    fun getStringRes(): Int {
        return when(this) {
            Kg -> R.string.unit_of_measurement_kilogram
            G -> R.string.unit_of_measurement_gram
            Mg -> R.string.unit_of_measurement_milligram
            Unit -> R.string.unit_of_measurement_unit
            L -> R.string.unit_of_measurement_liter
            Ml -> R.string.unit_of_measurement_milliliter
        }
    }
}