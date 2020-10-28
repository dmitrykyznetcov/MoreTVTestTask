package ru.dmitrykuznetsov.testtask.presentation.feature.info

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.dmitrykuznetsov.testtask.R
import ru.dmitrykuznetsov.testtask.databinding.ActivityEventContainerBinding
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem
import ru.dmitrykuznetsov.testtask.presentation.base.BaseActivity
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.EventItem
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.MoveItem
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.NoticeItem
import ru.dmitrykuznetsov.testtask.presentation.feature.info.fragments.EventInfoFragment
import ru.dmitrykuznetsov.testtask.presentation.feature.info.fragments.MoveInfoFragment
import ru.dmitrykuznetsov.testtask.presentation.feature.info.fragments.NoticeInfoFragment

class EventContainerActivity: BaseActivity<ActivityEventContainerBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_event_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        val event = intent?.getParcelableExtra(EVENT_KEY) as? DelegateAdapterItem
            ?: error("Не передан обязательный аргумент $EVENT_KEY")

        val fragment = when (event) {
            is EventItem -> EventInfoFragment.newInstance(event)
            is MoveItem -> MoveInfoFragment.newInstance(event)
            is NoticeItem -> NoticeInfoFragment.newInstance(event)
            else -> {
                showError(R.string.event_container_error)
                return
            }
        }

        supportFragmentManager.inTransactionNow { replace(R.id.fragment_container, fragment, fragment::class.java.name) }
    }

    /** Выполнение функции [func] внутри транзакции перед коммитом */
    private fun FragmentManager.inTransactionNow(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commitNow()
    }

    companion object {

        const val EVENT_KEY = "EVENT_KEY"
    }
}