package ru.dmitrykuznetsov.testtask.domain.repository

import io.reactivex.Single
import ru.dmitrykuznetsov.testtask.data.model.i.IEvent

/** Интерфейс репозитория событий */
interface IEventRepository {

    fun getEvents(): Single<List<IEvent>>
}