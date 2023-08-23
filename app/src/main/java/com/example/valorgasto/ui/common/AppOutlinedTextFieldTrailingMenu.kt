package com.example.valorgasto.ui.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.valorgasto.ui.theme.ValorGastoTheme

@Composable
fun AppOutlinedTextFieldTrailingMenu(
    value: String,
    onValueChange: (String) -> Unit,
    selectedItem: String,
    selectionChoices: List<String>,
    onItemSelection: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    charLimit: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    supportingText: String? = null,
    singleLine: Boolean = false,
    label: @Composable (() -> Unit)? = null,
    showSelectedOptionText: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None
    ) {

    var showExpandedDropdownMenu by remember { mutableStateOf(false) }

    AppOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = {

            val iconRotation by animateFloatAsState(
                targetValue = if (!showExpandedDropdownMenu) 0f else 180f,
                label = ""
            )
            Column(Modifier.wrapContentWidth()) {
                IconButton(onClick = { showExpandedDropdownMenu = !showExpandedDropdownMenu }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (!showSelectedOptionText) Text(text = selectedItem)
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.rotate(iconRotation)
                        )
                    }
                }
                DropdownMenu(
                    expanded = showExpandedDropdownMenu,
                    onDismissRequest = { showExpandedDropdownMenu = false }) {
                    selectionChoices.forEach { item ->
                        DropdownMenuItem(text = { Text(text = item) }, onClick = {
                            onItemSelection(item)
                            showExpandedDropdownMenu = false
                        })
                    }
                }
            }
        },
        charLimit = charLimit,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        errorMessage = errorMessage,
        supportingText = supportingText,
        label = label,
        visualTransformation = visualTransformation
    )
}

@Preview
@Composable
fun AppOutlinedTextFieldWithSelectionPrev() {
    ValorGastoTheme {
        Surface {
            val selectionChoices = listOf("Kg", "g", "L", "ml")
            var selectedItem by remember { mutableStateOf(selectionChoices[0]) }
            var text by remember { mutableStateOf("") }
            AppOutlinedTextFieldTrailingMenu(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.padding(18.dp),
                label = { Text(text = "Texto de teste para label") },
                selectionChoices = selectionChoices,
                onItemSelection = { selectedItem = it },
                selectedItem = selectedItem,
                supportingText = "Texto de suporte",
                charLimit = 3
            )
        }
    }
}