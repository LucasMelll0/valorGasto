package com.example.valorgasto.ui.purchase_form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.valorgasto.R
import com.example.valorgasto.ui.common.AppDateTextField
import com.example.valorgasto.ui.common.AppTextField
import com.example.valorgasto.ui.theme.ValorGastoTheme

@Composable
fun PurchaseFormScreen() {
    var purchaseLocation by rememberSaveable { mutableStateOf("") }
    var purchaseDate by rememberSaveable { mutableLongStateOf(System.currentTimeMillis()) }
    Scaffold(
        topBar = { AppToolbar(title = "Registrar Compras") },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) { paddingValues ->
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            shape = RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.default_corner_radius),
                topEnd = dimensionResource(
                    id = R.dimen.default_corner_radius
                )
            ),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_padding)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        dimensionResource(id = R.dimen.large_padding)
                    )
            ) {
                AppTextField(
                    value = purchaseLocation,
                    onValueChange = { purchaseLocation = it },
                    label = {
                        Text(text = "Nome do local onde comprou")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.LocationOn,
                            contentDescription = "local"
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                AppDateTextField(
                    selectedDate = purchaseDate,
                    onDateSelection = { purchaseDate = it },
                    label = { Text(text = "Selecione a data de compra") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Text(text = "Total: R$ 500,00")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(title: String? = null, subtitle: String? = null) {
    Column {
        TopAppBar(title = {}, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.content_description_go_back)
                )
            }
        }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent))
        Column(
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.large_padding),
                horizontal = dimensionResource(id = R.dimen.large_padding)
            )
        ) {
            title?.let { Text(text = title, style = MaterialTheme.typography.displaySmall) }
            subtitle?.let { Text(text = subtitle, style = MaterialTheme.typography.headlineSmall) }
        }
    }
}


@Preview
@Composable
fun PurchaseFormScreenPrev() {
    ValorGastoTheme {
        PurchaseFormScreen()
    }
}