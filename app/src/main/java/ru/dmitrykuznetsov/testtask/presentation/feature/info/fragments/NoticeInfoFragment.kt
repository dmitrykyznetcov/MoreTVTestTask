package ru.dmitrykuznetsov.testtask.presentation.feature.info.fragments

import android.os.Bundle
import android.view.View
import ru.dmitrykuznetsov.testtask.R
import ru.dmitrykuznetsov.testtask.databinding.FragmentNoticeInfoBinding
import ru.dmitrykuznetsov.testtask.presentation.base.BaseFragment
import ru.dmitrykuznetsov.testtask.presentation.feature.events.model.NoticeItem

class NoticeInfoFragment: BaseFragment<FragmentNoticeInfoBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_notice_info

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.item = arguments?.getParcelable(EXTRA_KEY_EVENT)
            ?: error("Не передан обязательный аргумент $EXTRA_KEY_EVENT")
    }

    companion object {

        /** Ключ получения события */
        private const val EXTRA_KEY_EVENT = "EXTRA_KEY_EVENT"

        fun newInstance(event: NoticeItem): NoticeInfoFragment {
            return NoticeInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_KEY_EVENT, event)
                }
            }
        }
    }
}