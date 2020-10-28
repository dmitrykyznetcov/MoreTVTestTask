package ru.dmitrykuznetsov.testtask.presentation.feature.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.dmitrykuznetsov.testtask.R
import ru.dmitrykuznetsov.testtask.databinding.ItemNoticeBinding
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.NoticeItem

class NoticeItemAdapter: AbsListItemAdapterDelegate<NoticeItem, DelegateAdapterItem, NoticeItemAdapter.ViewHolder>() {

    /** Слушатель нажатия на элемент адаптера */
    var onItemClickListener: (item: NoticeItem) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = DataBindingUtil.inflate<ItemNoticeBinding>(LayoutInflater.from(parent.context), R.layout.item_notice, parent, false)
        return ViewHolder(view)
    }

    override fun isForViewType(item: DelegateAdapterItem, items: MutableList<DelegateAdapterItem>, position: Int): Boolean =
        item is NoticeItem

    override fun onBindViewHolder(item: NoticeItem, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {

        /** Биндит элемент на его разметку */
        fun bind(item: NoticeItem) {
            val ctx = binding.root.context
            with(binding) {
                tvDate.text = ctx.getString(R.string.item_notice_date, item.flightDate)
                tvGate.text = ctx.getString(R.string.item_notice_gate, item.gate)
                root.setOnClickListener { onItemClickListener.invoke(item) }
            }
        }
    }
}