package ru.dmitrykuznetsov.testtask.presentation.feature.events.model

import kotlinx.android.parcel.Parcelize
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem

@Parcelize
data class MoveItem(
        var fromPlace: String? = null,
        var toPlace: String? = null,
        var estimateTime: Double? = null
) : DelegateAdapterItem {

    override fun id(): Any = hashCode()

    override fun content(): Any = hashCode()
}