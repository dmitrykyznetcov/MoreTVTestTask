package ru.dmitrykuznetsov.testtask.presentation.base.mvp

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner

/** Интерфейс базовой view */
interface MvpView : LifecycleOwner {

    fun showLoading(isDisplayed: Boolean)

    fun showError(@StringRes resId: Int)
}