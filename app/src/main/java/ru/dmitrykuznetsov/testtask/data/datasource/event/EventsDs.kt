package ru.dmitrykuznetsov.testtask.data.datasource.event

import ru.dmitrykuznetsov.testtask.common.truncate
import ru.dmitrykuznetsov.testtask.data.datasource.event.i.IEventsDs
import ru.dmitrykuznetsov.testtask.data.enums.EventName
import ru.dmitrykuznetsov.testtask.data.enums.EventType
import ru.dmitrykuznetsov.testtask.data.enums.GateTypes
import ru.dmitrykuznetsov.testtask.data.enums.MoveDirections
import ru.dmitrykuznetsov.testtask.data.model.Event
import ru.dmitrykuznetsov.testtask.data.model.Move
import ru.dmitrykuznetsov.testtask.data.model.Notice
import ru.dmitrykuznetsov.testtask.data.model.i.IEvent
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

/** Источник данных событий */
class EventsDs : IEventsDs {

    override fun request(): List<IEvent> {
        val eventsList = mutableListOf<IEvent>()
        // формируем рандомное количество элементов
        val itemCount = (10..100).random()
        // заполняем список рандомными событиями
        repeat(itemCount) {
            val nextEvent = getEventByType(EventType.values().random())
            eventsList.add(nextEvent)
        }
        return eventsList
    }

    companion object {

        fun getEventByType(type: EventType): IEvent {
            return when (type) {
                EventType.NOTICE -> getNotice()
                EventType.EVENT -> getEvent()
                EventType.MOVE -> getMove()
            }
        }

        private fun getNotice(): IEvent {
            val randomDate = Calendar.getInstance().truncate()
            randomDate.add(Calendar.DATE, (-30..0).random())
            val gate = GateTypes.values().random().value
            return Notice(
                flightDate = randomDate.time,
                gate = gate
            )
        }

        private fun getEvent(): IEvent {
            val startTime = Calendar.getInstance().truncate().apply { add(Calendar.DATE, (-20..20).random())}
            val endTime = Calendar.getInstance().truncate().apply { add(Calendar.DATE, (20..40).random()) }
            val eventName = EventName.values().random().value
            return Event(
                startTime = startTime.time,
                endTime = endTime.time,
                name = eventName
            )
        }

        private fun getMove(): IEvent {
            val from = MoveDirections.values().random()
            var to = from
            while (from == to) {
                to = MoveDirections.values().random()
            }
            val df = DecimalFormat("#,##")
            df.roundingMode = RoundingMode.CEILING
            val estimateTime = df.format(Random().nextDouble() * 60).toDouble()
            return Move(
                fromPlace = from.value,
                toPlace = to.value,
                estimateTime = estimateTime
            )
        }
    }
}