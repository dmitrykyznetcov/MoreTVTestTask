package ru.dmitrykuznetsov.testtask.presentation.feature.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.dmitrykuznetsov.testtask.R
import ru.dmitrykuznetsov.testtask.databinding.ItemEventBinding
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.EventItem

class EventItemAdapter: AbsListItemAdapterDelegate<EventItem, DelegateAdapterItem, EventItemAdapter.ViewHolder>() {

    /** Слушатель нажатия на элемент адаптера */
    var onItemClickListener: (item: EventItem) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = DataBindingUtil.inflate<ItemEventBinding>(LayoutInflater.from(parent.context),R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun isForViewType(
        item: DelegateAdapterItem,
        items: MutableList<DelegateAdapterItem>,
        position: Int
    ): Boolean {
        return item is EventItem
    }

    override fun onBindViewHolder(item: EventItem, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {

        /** Биндит элемент на его разметку */
        fun bind(item: EventItem) {
            val ctx = binding.root.context
            with(binding) {
                tvHeader.text = ctx.getString(R.string.item_event_header,item.name)
                tvDescription.text = ctx.getString(R.string.item_event_description, item.startTime, item.endTime)
                root.setOnClickListener { onItemClickListener.invoke(item) }
            }
        }
    }
}