package ru.dmitrykuznetsov.testtask.presentation.base.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/** Интерфейс наблюдателя жизненного цикла [Lifecycle] компонента */
interface ViewLifecycleObserver : LifecycleObserver {

    /** Функции жизненного цикла для вызова их системой через аннотации */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onViewAnyEvent(source: LifecycleOwner, event: Lifecycle.Event)

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onViewDestroy()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onViewCreate()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onViewPause()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onViewResume()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onViewStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onViewStop()
}