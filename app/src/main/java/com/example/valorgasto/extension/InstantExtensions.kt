package com.example.valorgasto.extension

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val PATTERN_FORMAT = "dd/MMM/yyyy"
fun Instant.toFormattedString(): String {
    val formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
        .withZone(ZoneId.systemDefault())
    return formatter.format(this)
}