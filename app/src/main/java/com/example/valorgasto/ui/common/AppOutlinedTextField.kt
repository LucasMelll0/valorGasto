package com.example.valorgasto.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.valorgasto.R
import com.example.valorgasto.ui.theme.ValorGastoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    charLimit: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    errorMessage: String? = null,
    supportingText: String? = null,
    label: @Composable() (() -> Unit)? = null,
    readOnly: Boolean = false
) {
    val getErrorMessage: @Composable () -> String = {
        errorMessage
            ?: if (value.isNotEmpty()) stringResource(id = R.string.common_text_field_error_message) else stringResource(
                id = R.string.common_empty_text_field_error_message
            )
    }
    OutlinedTextField(
        value = value,
        readOnly = readOnly,
        onValueChange = { text ->
            charLimit?.let { charLimit ->
                if (text.length <= charLimit) onValueChange(text)
            } ?: onValueChange(text)
        },
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        supportingText = {
            if (isError) Text(text = getErrorMessage()) else {
                charLimit?.let { charLimit ->
                    if (value.isNotEmpty()) Text(
                        text = stringResource(
                            id = R.string.common_text_field_char_limit_text_place_holder,
                            value.length,
                            charLimit
                        )
                    ) else {
                        supportingText?.let {
                            Text(text = it)
                        }
                    }
                }
            }
        },
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        label = label,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors()
    )
}

@Preview
@Composable
fun AppOutlinedTextFieldPrev() {
    ValorGastoTheme {
        var text by remember { mutableStateOf("") }
        val isError: @Composable () -> Boolean = {
            text.isDigitsOnly() && text.isNotEmpty()
        }
        Surface {
            AppOutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.padding(18.dp),
                isError = isError(),
                charLimit = 10,
                supportingText = "Texto de suporte de teste",
                label = { Text(text = "Label de teste") },
                readOnly = true
            )
        }
    }
}