package com.faroh.airplaneandroid.core.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

object Formatter {
    fun rupiahFormatter(price: Int): String {
        val kursId = DecimalFormat.getCurrencyInstance() as DecimalFormat
        val rupiah = DecimalFormatSymbols()
        rupiah.groupingSeparator = '.'
        rupiah.currencySymbol = "IDR "

        kursId.decimalFormatSymbols = rupiah
        val result = kursId.format(price)

        return result.substring(0, result.indexOf(','))
    }
}