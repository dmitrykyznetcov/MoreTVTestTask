package ru.dmitrykuznetsov.testtask.presentation.base.mvp

/** Интерфейс базового презентера. */
interface MvpPresenter<V : MvpView> {

    /** Связывание презентера с view */
    fun onAttach(view: V)

    /** Завершение работы с view */
    fun onDetach()
}