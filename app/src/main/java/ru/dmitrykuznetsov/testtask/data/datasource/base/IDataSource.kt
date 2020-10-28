package ru.dmitrykuznetsov.testtask.data.datasource.base

/** Интерфейс источника данных */
interface IDataSource<Data> {

    /** Получить данные по запросу */
    fun request(): Data
}