package com.example.valorgasto.model

import com.example.valorgasto.utils.CurrencyAmountInputVisualTransformation
import com.example.valorgasto.utils.ProductCategories
import com.example.valorgasto.utils.UnitOfMeasurement
import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val quantity: Double,
    val unitOfMeasurement: UnitOfMeasurement,
    val price: Long,
    val category: ProductCategories
) {
    override fun toString(): String {
        return "$name \n$quantity \n$unitOfMeasurement \n${
            CurrencyAmountInputVisualTransformation.formatText(price.toString())
        } \n$category"
    }
}