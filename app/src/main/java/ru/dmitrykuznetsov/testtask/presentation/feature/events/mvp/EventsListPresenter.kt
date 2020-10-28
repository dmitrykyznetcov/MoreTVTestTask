package ru.dmitrykuznetsov.testtask.presentation.feature.events.mvp

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.dmitrykuznetsov.testtask.R
import ru.dmitrykuznetsov.testtask.domain.repository.IEventRepository
import ru.dmitrykuznetsov.testtask.presentation.base.mvp.BasePresenter
import ru.dmitrykuznetsov.testtask.presentation.feature.events.mapper.EventsMapper
import java.util.concurrent.TimeUnit

class EventsListPresenter(
    private val repository: IEventRepository
) :BasePresenter<EventsListMvp>() {

    override fun onAttach(view: EventsListMvp) {
        super.onAttach(view)
        getEvents()
    }

    fun onSwipeRefresh() {
        getEvents()
    }

    private fun getEvents() {
        repository.getEvents()
            .delay(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .map(EventsMapper::map)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view?.setTitle(R.string.event_list_loading,)
                view?.showLoading(true)
            }
            .doFinally { view?.showLoading(false) }
            .subscribe({
                view?.setTitle(R.string.event_list_count, it.size.toString())
                view?.renderList(it)
            },{
                Log.d(this::class.java.name, "Ошибка при получении списка событий")
                view?.showError(R.string.events_get_error)
            })
            .addToPresenter()
    }
}