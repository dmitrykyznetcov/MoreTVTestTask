package ru.dmitrykuznetsov.testtask.data.repository

import io.reactivex.Single
import ru.dmitrykuznetsov.testtask.data.datasource.event.i.IEventsDs
import ru.dmitrykuznetsov.testtask.data.model.i.IEvent
import ru.dmitrykuznetsov.testtask.domain.repository.IEventRepository

/** Репозиторий событий */
class EventsRepository(
        private val dataSource: IEventsDs
) : IEventRepository {

    override fun getEvents(): Single<List<IEvent>> =
            Single.fromCallable { dataSource.request() }
}