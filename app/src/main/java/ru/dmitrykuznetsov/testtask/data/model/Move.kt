package ru.dmitrykuznetsov.testtask.data.model

import ru.dmitrykuznetsov.testtask.data.model.i.IEvent

data class Move(
        var fromPlace: String? = null,
        var toPlace: String? = null,
        var estimateTime: Double? = null
) : IEvent