package ru.dmitrykuznetsov.testtask.data.model

import ru.dmitrykuznetsov.testtask.data.model.i.IEvent
import java.util.*

data class Notice(
        val flightDate: Date? = null,
        val gate: String? = null
) : IEvent