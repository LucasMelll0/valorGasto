package com.example.valorgasto.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat

class CurrencyAmountInputVisualTransformation : VisualTransformation {


    companion object {
        private val symbols = DecimalFormat().decimalFormatSymbols
        private const val numberOfDecimals = 2
        fun formatText(text: String): String {
            val thousandsSeparator = symbols.groupingSeparator
            val decimalSeparator = symbols.decimalSeparator
            val zero = symbols.zeroDigit
            val intPart = text
                .dropLast(numberOfDecimals)
                .reversed()
                .chunked(3)
                .joinToString(thousandsSeparator.toString())
                .reversed()
                .ifEmpty {
                    zero.toString()
                }
            val fractionPart = text.takeLast(2).let {
                if (it.length != numberOfDecimals) {
                    List(numberOfDecimals - it.length) {
                        zero
                    }.joinToString("") + it
                } else it
            }
            return intPart + decimalSeparator + fractionPart
        }
    }


    override fun filter(text: AnnotatedString): TransformedText {
        val inputText = text.text
        val formattedNumber = formatText(inputText)
        val newText = AnnotatedString(
            text = formattedNumber,
            spanStyles = text.spanStyles,
            paragraphStyles = text.paragraphStyles
        )

        val offsetMapping = FixedCursorOffsetMapping(
            contentLength = inputText.length,
            formattedContentLength = formattedNumber.length
        )
        return TransformedText(newText, offsetMapping)
    }

    private class FixedCursorOffsetMapping(
        private val contentLength: Int,
        private val formattedContentLength: Int
    ) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = formattedContentLength

        override fun transformedToOriginal(offset: Int): Int = contentLength
    }

}