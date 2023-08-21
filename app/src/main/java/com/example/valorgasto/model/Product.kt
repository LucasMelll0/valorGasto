package com.example.valorgasto.model

import com.example.valorgasto.utils.ProductCategories
import com.example.valorgasto.utils.UnitOfMeasurement
import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val quantity: Double,
    val unitOfMeasurement: UnitOfMeasurement,
    val price: Double,
    val category: ProductCategories
)