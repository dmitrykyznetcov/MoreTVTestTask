package ru.dmitrykuznetsov.testtask.presentation.base.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/** Пустая реализация наблюдателя жизненного цикла [Lifecycle] компонента */
open class EmptyViewLifecycleObserver : ViewLifecycleObserver {

    override fun onViewAnyEvent(source: LifecycleOwner, event: Lifecycle.Event) {}

    override fun onViewDestroy() {}

    override fun onViewCreate() {}

    override fun onViewPause() {}

    override fun onViewResume() {}

    override fun onViewStart() {}

    override fun onViewStop() {}
}