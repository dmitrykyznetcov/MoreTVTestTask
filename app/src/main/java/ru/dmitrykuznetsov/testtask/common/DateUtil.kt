package ru.dmitrykuznetsov.testtask.common

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    /** Паттерн конвертирования времени из строки */
    const val DEFAULT_DATE_PATTERN = "dd.MM.yyyy"

    /** Возвращает дату в виде строки по шаблону или пустую строку */
    fun getStringFromDate(date: Date?, pattern: String = DEFAULT_DATE_PATTERN): String =
        try {
            SimpleDateFormat(pattern).format(date)
        } catch (_: Exception) {
            null
        }.orEmpty()
}

