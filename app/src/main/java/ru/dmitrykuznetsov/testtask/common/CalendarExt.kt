package ru.dmitrykuznetsov.testtask.common

import java.util.*

/** Обнуляет часы, минуты, секунды, миллисекунды */
fun Calendar.truncate(): Calendar {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
    return this
}