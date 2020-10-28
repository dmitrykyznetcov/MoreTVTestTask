package ru.dmitrykuznetsov.testtask.presentation.feature.events.mapper

import ru.dmitrykuznetsov.testtask.common.DateUtil
import ru.dmitrykuznetsov.testtask.data.model.Event
import ru.dmitrykuznetsov.testtask.data.model.Move
import ru.dmitrykuznetsov.testtask.data.model.Notice
import ru.dmitrykuznetsov.testtask.data.model.i.IEvent
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.EventItem
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.MoveItem
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.NoticeItem

object EventsMapper {

    fun map(from: List<IEvent>): List<DelegateAdapterItem> {
        val list = mutableListOf<DelegateAdapterItem>()
        from.map { event ->
            when (event) {
                is Event -> list.add(prepareEventItem(event))
                is Notice -> list.add(prepareNoticeItem(event))
                is Move -> list.add(prepareMoveItem(event))
                else -> return@map
            }
        }
        return list
    }

    private fun prepareNoticeItem(event: Notice): DelegateAdapterItem {
        return NoticeItem(
            flightDate = DateUtil.getStringFromDate(event.flightDate),
            gate = event.gate.orEmpty()
        )
    }

    private fun prepareEventItem(event: Event): DelegateAdapterItem {
        return EventItem(
            startTime = DateUtil.getStringFromDate(event.startTime),
            endTime = DateUtil.getStringFromDate(event.endTime),
            name = event.name.orEmpty()
        )
    }

    private fun prepareMoveItem(event: Move): DelegateAdapterItem {
        return MoveItem(
            fromPlace = event.fromPlace,
            toPlace = event.toPlace,
            estimateTime = event.estimateTime
        )
    }
}