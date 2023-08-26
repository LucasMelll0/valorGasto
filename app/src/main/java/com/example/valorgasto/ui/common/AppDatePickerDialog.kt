package com.example.valorgasto.ui.common

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.valorgasto.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePickerDialog(
    onConfirm: (dateInMillis: Long) -> Unit,
    onDismissRequest: () -> Unit,
    initialSelectedDateMillis: Long
) {
    val datePickerState =
        rememberDatePickerState(
            initialSelectedDateMillis = initialSelectedDateMillis,
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis <= System.currentTimeMillis()
                }
            })
    DatePickerDialog(onDismissRequest = onDismissRequest, confirmButton = {
        TextButton(onClick = {
            datePickerState.selectedDateMillis?.let {
                onConfirm(it)
            }
        }) {
            Text(text = stringResource(id = R.string.common_confirm))
        }
    },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.common_cancel))
            }
        }) {
        DatePicker(state = datePickerState)
    }
}