package ru.dmitrykuznetsov.testtask.presentation.feature.events.model

import kotlinx.android.parcel.Parcelize
import ru.dmitrykuznetsov.testtask.common.EMPTY
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem

@Parcelize
data class NoticeItem(
        val flightDate: String = String.EMPTY,
        val gate: String = String.EMPTY
) : DelegateAdapterItem {

    override fun id(): Any = hashCode()

    override fun content(): Any = hashCode()
}