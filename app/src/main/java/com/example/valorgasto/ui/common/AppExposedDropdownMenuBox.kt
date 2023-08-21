package com.example.valorgasto.ui.common

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.valorgasto.ui.theme.ValorGastoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppExposedDropdownMenuBox(
    selectedItem: String,
    onSelectItem: (String) -> Unit,
    selectionChoices: List<String>,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
        modifier = modifier
    ) {
        AppTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            selectionChoices.forEach { item ->
                DropdownMenuItem(text = { Text(text = item) }, onClick = { onSelectItem(item)
                isExpanded = false})
            }
        }
    }
}

@Preview
@Composable
fun AppExposedDropdownMenuBoxPrev() {
    ValorGastoTheme {
        val selectionChoices = listOf("Limpeza", "Higiene", "Frutas", "Carnes", "Bebidas")
        var selected by remember { mutableStateOf(selectionChoices.random()) }
        AppExposedDropdownMenuBox(
            selectedItem = selected,
            onSelectItem = { selected = it },
            selectionChoices = selectionChoices
        )
    }
}