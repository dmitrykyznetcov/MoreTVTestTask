package ru.dmitrykuznetsov.testtask.data.datasource.event.i

import ru.dmitrykuznetsov.testtask.data.datasource.base.IDataSource
import ru.dmitrykuznetsov.testtask.data.model.i.IEvent

/** Интерфейс источника данных ленты событий */
interface IEventsDs: IDataSource<List<IEvent>>