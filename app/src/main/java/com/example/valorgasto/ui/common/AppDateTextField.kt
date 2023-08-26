package com.example.valorgasto.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import com.example.valorgasto.extension.toFormattedString
import java.time.Instant

@Composable
fun AppDateTextField(
    selectedDate: Long,
    onDateSelection: (dateInMillis: Long) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    var showDatePickerDialog by rememberSaveable { mutableStateOf(false) }
    val currentDate = Instant.ofEpochMilli(selectedDate)
    val focusManager = LocalFocusManager.current
    if (showDatePickerDialog) AppDatePickerDialog(
        onConfirm = {
            showDatePickerDialog = false
            onDateSelection(it)
        },
        onDismissRequest = { showDatePickerDialog = false },
        initialSelectedDateMillis = selectedDate
    )
    AppTextField(
        value = currentDate.toFormattedString(),
        onValueChange = {},
        readOnly = true,
        label = label,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null
            )
        },
        modifier = modifier.onFocusEvent {
            if (it.isFocused) {
                showDatePickerDialog = true
                focusManager.clearFocus(force = true)
            }
        })
}