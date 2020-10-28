package ru.dmitrykuznetsov.testtask.presentation.feature.events

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import ru.dmitrykuznetsov.testtask.R
import ru.dmitrykuznetsov.testtask.common.scrollToTopOnChanges
import ru.dmitrykuznetsov.testtask.data.datasource.event.EventsDs
import ru.dmitrykuznetsov.testtask.data.datasource.event.i.IEventsDs
import ru.dmitrykuznetsov.testtask.data.repository.EventsRepository
import ru.dmitrykuznetsov.testtask.databinding.ActivityEventsListBinding
import ru.dmitrykuznetsov.testtask.domain.repository.IEventRepository
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem
import ru.dmitrykuznetsov.testtask.presentation.adapter.DiffUtilDelegatesAdapter
import ru.dmitrykuznetsov.testtask.presentation.base.BaseActivity
import ru.dmitrykuznetsov.testtask.presentation.feature.events.adapter.EventItemAdapter
import ru.dmitrykuznetsov.testtask.presentation.feature.events.adapter.MoveItemAdapter
import ru.dmitrykuznetsov.testtask.presentation.feature.events.adapter.NoticeItemAdapter
import ru.dmitrykuznetsov.testtask.presentation.feature.events.mvp.EventsListMvp
import ru.dmitrykuznetsov.testtask.presentation.feature.events.mvp.EventsListPresenter
import ru.dmitrykuznetsov.testtask.presentation.feature.info.EventContainerActivity

/** Активити экрана со списком собитий */
class EventsListActivity : BaseActivity<ActivityEventsListBinding>(), EventsListMvp {

    override val layoutId: Int
        get() = R.layout.activity_events_list

    private val eventDs: IEventsDs = EventsDs()
    private val eventRepository: IEventRepository = EventsRepository(eventDs)
    private val presenter = EventsListPresenter(eventRepository)

    /** Адаптер */
    private val adapter = DiffUtilDelegatesAdapter.Builder()
        .add(EventItemAdapter().apply { onItemClickListener = ::onItemClickListener })
        .add(NoticeItemAdapter().apply { onItemClickListener = ::onItemClickListener })
        .add(MoveItemAdapter().apply { onItemClickListener = ::onItemClickListener })
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initScreen()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun renderList(list: List<DelegateAdapterItem>) {
        adapter.items = list
    }

    override fun showLoading(isDisplayed: Boolean) {
        binding.refreshLayout.isRefreshing = isDisplayed
    }

    override fun setTitle(@StringRes resId: Int, vararg attrs: String) {
        binding.toolbar.title = getString(resId, *attrs)
    }

    private fun initScreen() {
        with(binding) {
            rvEvents.apply {
                adapter = this@EventsListActivity.adapter
                scrollToTopOnChanges()
            }
            refreshLayout.setOnRefreshListener {
                presenter.onSwipeRefresh()
            }
        }
    }

    private fun onItemClickListener(event: DelegateAdapterItem) {
        val intent = Intent(this, EventContainerActivity::class.java).apply {
            putExtra(EventContainerActivity.EVENT_KEY, event)
        }
        startActivity(intent)
    }
}