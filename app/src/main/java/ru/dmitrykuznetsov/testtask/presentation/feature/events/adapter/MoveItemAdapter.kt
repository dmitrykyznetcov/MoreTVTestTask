package ru.dmitrykuznetsov.testtask.presentation.feature.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.dmitrykuznetsov.testtask.R
import ru.dmitrykuznetsov.testtask.databinding.ItemMoveBinding
import ru.dmitrykuznetsov.testtask.presentation.adapter.DelegateAdapterItem
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.MoveItem

class MoveItemAdapter: AbsListItemAdapterDelegate<MoveItem, DelegateAdapterItem, MoveItemAdapter.ViewHolder>() {

    /** Слушатель нажатия на элемент адаптера */
    var onItemClickListener: (item: MoveItem) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = DataBindingUtil.inflate<ItemMoveBinding>(LayoutInflater.from(parent.context), R.layout.item_move, parent, false)
        return ViewHolder(view)
    }

    override fun isForViewType(item: DelegateAdapterItem, items: MutableList<DelegateAdapterItem>, position: Int): Boolean {
        return item is MoveItem
    }

    override fun onBindViewHolder(item: MoveItem, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemMoveBinding) : RecyclerView.ViewHolder(binding.root) {

        /** Биндит элемент на его разметку */
        fun bind(item: MoveItem) {
            val ctx = binding.root.context
            with(binding) {
                tvRouteFrom.text = ctx.getString(R.string.item_move_route_from, item.fromPlace)
                tvRouteTo.text = ctx.getString(R.string.item_move_route_to, item.toPlace)
                tvEstimateTime.text = ctx.getString(R.string.item_move_estimate_time, item.estimateTime)
                root.setOnClickListener { onItemClickListener.invoke(item) }
            }
        }
    }
}