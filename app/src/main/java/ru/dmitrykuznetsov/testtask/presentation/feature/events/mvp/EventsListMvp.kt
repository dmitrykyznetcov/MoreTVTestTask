package ru.dmitrykuznetsov.testtask.presentation.feature.events.mvp

import androidx.annotation.StringRes
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem
import ru.dmitrykuznetsov.testtask.presentation.base.mvp.MvpView

/** View - экрана списка событий */
interface EventsListMvp: MvpView {

    fun renderList(list: List<DelegateAdapterItem>)

    fun setTitle(@StringRes resId: Int, vararg attrs: String)
}