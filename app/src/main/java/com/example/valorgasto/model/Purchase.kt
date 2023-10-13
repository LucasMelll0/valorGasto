package com.example.valorgasto.model

class Purchase(
    var name: String,
    var date: String,
    var products: List<Product>,
    var totalValue: Float
)