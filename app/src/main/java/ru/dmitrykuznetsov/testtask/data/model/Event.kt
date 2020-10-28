package ru.dmitrykuznetsov.testtask.data.model

import ru.dmitrykuznetsov.testtask.data.model.i.IEvent
import java.util.*

data class Event(
        var startTime: Date? = null,
        var endTime: Date? = null,
        var name: String? = null
) : IEvent