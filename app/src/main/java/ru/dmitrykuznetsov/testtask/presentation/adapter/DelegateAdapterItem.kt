package ru.dmitrykuznetsov.testtask.presentation.adapter

import android.os.Parcelable

interface DelegateAdapterItem: Parcelable {

    /** Идентификатор */
    fun id(): Any

    /** Контент для определения необходимости обновления изменений 2-х одинаковых элементов */
    fun content(): Any
}