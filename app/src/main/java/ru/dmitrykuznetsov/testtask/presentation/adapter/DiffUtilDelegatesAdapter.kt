package ru.dmitrykuznetsov.testtask.presentation.adapter

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

open class DiffUtilDelegatesAdapter protected constructor(
        vararg delegates: AdapterDelegate<List<DelegateAdapterItem>>
) : AsyncListDifferDelegationAdapter<DelegateAdapterItem>(ComparableItemDiffCallback()) {

    init {
        delegates.forEach {
            delegatesManager.addDelegate(it)
        }
    }

    class Builder {

        /** Набор делегатов */
        private val delegates = HashSet<AdapterDelegate<List<DelegateAdapterItem>>>()

        /** Добавляет делегат */
        fun add(delegate: AdapterDelegate<List<DelegateAdapterItem>>): Builder {
            delegates.add(delegate)
            return this
        }

        /** Создает экземпляр адаптера */
        fun build(): DiffUtilDelegatesAdapter =
                DiffUtilDelegatesAdapter(*delegates.toTypedArray())
    }
}
