package ru.dmitrykuznetsov.testtask.common

import androidx.recyclerview.widget.RecyclerView

/** Добавляет прокрутку RecyclerView к первому элементу при обновлениях элементов адаптера */
fun RecyclerView.scrollToTopOnChanges(): RecyclerView.AdapterDataObserver? {
    return adapter?.addChangeListListener { scrollToPosition(0) }
}

/** Добавить слушателя изменения списка */
fun <VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.addChangeListListener(listener: () -> Unit): RecyclerView.AdapterDataObserver {
    val dataSetChangeObserver = object : RecyclerView.AdapterDataObserver() {

        override fun onChanged() {
            listener.invoke()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            onChanged()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            onChanged()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            onChanged()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            onChanged()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            onChanged()
        }
    }
    registerAdapterDataObserver(dataSetChangeObserver)
    return dataSetChangeObserver
}