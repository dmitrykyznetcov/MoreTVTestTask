package ru.dmitrykuznetsov.testtask.presentation.feature.events.model

import ru.dmitrykuznetsov.testtask.common.EMPTY
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventItem(
        var startTime: String = String.EMPTY,
        var endTime: String = String.EMPTY,
        var name: String = String.EMPTY
) : DelegateAdapterItem {
    override fun id(): Any = hashCode()

    override fun content(): Any = hashCode()
}