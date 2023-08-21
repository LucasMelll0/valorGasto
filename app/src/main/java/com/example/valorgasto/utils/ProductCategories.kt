package com.example.valorgasto.utils

import androidx.annotation.StringRes
import com.example.valorgasto.R

enum class ProductCategories {
    Fruits,
    Vegetables,
    Meats,
    Frozen,
    Snacks,
    Candies,
    Cleaning,
    Hygiene,
    Drinks,
    Bakery,
    Pets,
    Health,
    Electronics,
    Garden,
    Others;

    @StringRes
    fun getStringRes(): Int {
        return when(this) {
            Fruits -> R.string.product_category_fruits
            Vegetables -> R.string.product_category_vegetables
            Meats -> R.string.product_category_meats
            Frozen -> R.string.product_category_frozen
            Snacks -> R.string.product_category_Snacks
            Candies -> R.string.product_category_Candies
            Cleaning -> R.string.product_category_Cleaning
            Hygiene -> R.string.product_category_hygiene
            Drinks -> R.string.product_category_drinks
            Bakery -> R.string.product_category_bakery
            Pets -> R.string.product_category_pets
            Health -> R.string.product_category_health
            Electronics -> R.string.product_category_electronics
            Garden -> R.string.product_category_garden
            Others -> R.string.product_category_others
        }
    }
}