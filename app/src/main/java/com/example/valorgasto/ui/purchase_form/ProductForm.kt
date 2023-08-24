package com.example.valorgasto.ui.purchase_form

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.text.isDigitsOnly
import com.example.valorgasto.R
import com.example.valorgasto.model.Product
import com.example.valorgasto.ui.common.AppExposedDropdownMenuBox
import com.example.valorgasto.ui.common.AppOutlinedTextField
import com.example.valorgasto.ui.common.AppOutlinedTextFieldTrailingMenu
import com.example.valorgasto.ui.theme.ValorGastoTheme
import com.example.valorgasto.utils.Currency
import com.example.valorgasto.utils.CurrencyAmountInputVisualTransformation
import com.example.valorgasto.utils.ProductCategories
import com.example.valorgasto.utils.UnitOfMeasurement

@Composable
fun ProductForm(
    onDismiss: () -> Unit,
    onConfirm: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        val context = LocalContext.current
        var productName by rememberSaveable { mutableStateOf("") }
        var productNameTextFieldHasBeenUsed by rememberSaveable { mutableStateOf(false) }
        val productNameHasError = productName.isEmpty() && productNameTextFieldHasBeenUsed
        var productQuantity by rememberSaveable { mutableStateOf("") }
        var productQuantityTextFieldHasBeenUsed by rememberSaveable { mutableStateOf(false) }
        val productQuantityHasError =
            productQuantity.isEmpty() && productQuantityTextFieldHasBeenUsed
        var selectedUnitOfMeasurement by rememberSaveable { mutableStateOf(UnitOfMeasurement.values()[0]) }
        var productPrice by rememberSaveable { mutableStateOf("") }
        var productPriceTextFieldHasBeenUsed by rememberSaveable { mutableStateOf(false) }
        var selectedCurrency by rememberSaveable { mutableStateOf(Currency.values()[0]) }
        val productPriceHasError = productPrice.isEmpty() && productPriceTextFieldHasBeenUsed
        var selectedCategory by rememberSaveable { mutableStateOf(ProductCategories.values()[0]) }

        Card(
            modifier = modifier.padding(dimensionResource(id = R.dimen.large_padding)),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(dimensionResource(id = R.dimen.large_padding))
            ) {
                Text(
                    text = stringResource(id = R.string.product_form_title),
                    style = MaterialTheme.typography.displaySmall.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.large_padding))
                )
                AppOutlinedTextField(
                    value = productName,
                    onValueChange = {
                        productName = it
                        productNameTextFieldHasBeenUsed = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    charLimit = 30,
                    singleLine = true,
                    isError = productNameHasError,
                    label = { Text(text = stringResource(id = R.string.product_form_name_label)) },
                )
                AppOutlinedTextFieldTrailingMenu(
                    value = productQuantity,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            productQuantity = it
                            productQuantityTextFieldHasBeenUsed = true
                        }
                    },
                    isError = productQuantityHasError,
                    selectedItem = stringResource(id = selectedUnitOfMeasurement.getStringRes()),
                    selectionChoices = UnitOfMeasurement.values()
                        .map { stringResource(id = it.getStringRes()) },
                    onItemSelection = { selected ->
                        UnitOfMeasurement.values().find {
                            context.getString(it.getStringRes()) == selected
                        }?.let {
                            selectedUnitOfMeasurement = it
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.product_form_quantity_label)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    modifier = Modifier.fillMaxWidth()
                )
                AppOutlinedTextFieldTrailingMenu(
                    value = productPrice,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            productPrice = it
                            productPriceTextFieldHasBeenUsed = true
                        }
                    },
                    selectedItem = selectedCurrency.toString(),
                    onItemSelection = { selected ->
                        Currency.values().find {
                            it.toString() == selected
                        }?.let {
                            selectedCurrency = it
                        }
                    },
                    selectionChoices = Currency.values().map { it.toString() },
                    isError = productPriceHasError,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Text(text = stringResource(id = selectedCurrency.getSymbolStringRes())) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    label = { Text(text = stringResource(id = R.string.product_form_price_label)) },
                    visualTransformation = CurrencyAmountInputVisualTransformation(),
                    showSelectedOptionText = true
                )
                AppExposedDropdownMenuBox(
                    label = { Text(text = stringResource(id = R.string.product_form_category_label)) },
                    selectedItem = stringResource(id = selectedCategory.getStringRes()),
                    onItemSelection = { item ->
                        ProductCategories.values().find {
                            context.getString(it.getStringRes()) == item
                        }?.let {
                            selectedCategory = it
                        }
                    },
                    selectionChoices = ProductCategories.values()
                        .map { stringResource(id = it.getStringRes()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.large_padding))
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(text = stringResource(id = R.string.common_cancel))
                    }
                    TextButton(onClick = {
                        val hasError = (productNameHasError || !productNameTextFieldHasBeenUsed) ||
                                (productQuantityHasError || !productQuantityTextFieldHasBeenUsed) ||
                                (productPriceHasError || !productPriceTextFieldHasBeenUsed)
                        if (!hasError) {
                            try {
                                val product = Product(
                                    name = productName,
                                    quantity = productQuantity.toInt(),
                                    unitOfMeasurement = selectedUnitOfMeasurement,
                                    price = productPrice.toLong(),
                                    currency = selectedCurrency,
                                    category = selectedCategory
                                )
                                onConfirm(product)
                                Log.d("TAG", "ProductForm: $product")
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.common_error_message),
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        } else {
                            productNameTextFieldHasBeenUsed = true
                            productQuantityTextFieldHasBeenUsed = true
                            productPriceTextFieldHasBeenUsed = true
                        }

                    }) {
                        Text(text = stringResource(id = R.string.common_confirm))
                    }
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun ProductFormPreview() {
    ValorGastoTheme {
        ProductForm(onConfirm = {}, onDismiss = {})
    }
}