package com.example.valorgasto.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.valorgasto.R
import com.example.valorgasto.ui.theme.ValorGastoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    charLimit: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    errorMessage: String = if (value.isNotEmpty()) stringResource(id = R.string.common_text_field_error_message) else stringResource(
        id = R.string.common_empty_text_field_error_message
    ),
    supportingText: String? = null,
    label: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = { text ->
            charLimit?.let { charLimit ->
                if (text.length <= charLimit) onValueChange(text)
            } ?: onValueChange(text)
        },
        readOnly = readOnly,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        supportingText = {
            if (isError) Text(text = errorMessage) else {
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
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    )
}

@Preview
@Composable
fun AppTextFieldPrev() {
    ValorGastoTheme {
        Surface {
            var text by remember { mutableStateOf("") }

            AppTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.default_padding))
                    .fillMaxWidth(),
                label = {
                    Text("Teste o text input aqui")
                },
                charLimit = 10,
                singleLine = true,
                supportingText = "Texto de suporte",
                trailingIcon = { Icon(imageVector = Icons.Filled.Check, contentDescription = null) }
            )
        }
    }
}