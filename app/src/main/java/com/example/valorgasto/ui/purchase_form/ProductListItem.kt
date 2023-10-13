package com.example.valorgasto.ui.purchase_form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.valorgasto.R
import com.example.valorgasto.model.Product
import com.example.valorgasto.ui.theme.ValorGastoTheme
import com.example.valorgasto.utils.ProductCategories
import com.example.valorgasto.utils.UnitOfMeasurement

@Composable
fun ProductListItem(product: Product, modifier: Modifier) {
    val price = "%.2f".format(product.price).replace(".", ",")
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.default_padding))
            .fillMaxWidth()
    ) {
        Text(text = product.name, style = MaterialTheme.typography.titleLarge)
        Text(text = "Preço: R$ $price")
        Text(text = "Quantidade: ${product.quantity} ${stringResource(id = product.unitOfMeasurement.getStringRes())}")
        Text(text = "Categoria: ${stringResource(id = product.category.getStringRes())}")
    }
}

@Preview
@Composable
fun ProductListItemPrev() {
    ValorGastoTheme {
        val product = Product(
            name = "Papel",
            price = 20.50f,
            quantity = 20,
            unitOfMeasurement = UnitOfMeasurement.Unit,
            category = ProductCategories.Hygiene
        )
        Surface {
            ProductListItem(product = product, modifier = Modifier)
        }
    }
}