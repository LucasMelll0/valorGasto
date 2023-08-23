package com.example.valorgasto.utils

import androidx.annotation.StringRes
import com.example.valorgasto.R

enum class Currency {
    BRL,
    USD,
    EUR,
    CNY,
    RUB;

    @StringRes
    fun getSymbolStringRes(): Int {
        return when(this) {
            BRL -> R.string.brl_symbol
            USD -> R.string.usd_symbol
            EUR -> R.string.eur_symbol
            CNY -> R.string.cny_symbol
            RUB -> R.string.rub_symbol
        }
    }
}