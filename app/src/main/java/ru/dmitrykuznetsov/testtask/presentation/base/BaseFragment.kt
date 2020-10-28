package ru.dmitrykuznetsov.testtask.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import ru.dmitrykuznetsov.testtask.presentation.base.mvp.MvpView

abstract class BaseFragment<B : ViewDataBinding> : Fragment(), MvpView {

    /** Идентификатор разметки */
    protected abstract val layoutId: Int
        @LayoutRes get

    /** Биндинг разметки */
    protected val binding: B
        get() = requireNotNull(_binding)

    /** Базовая активность, связанная с текущим фрагментом */
    protected val baseActivity: BaseActivity<*>?
        get() = activity as? BaseActivity<*>

    private var _binding: B? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showLoading(isDisplayed: Boolean) {
        baseActivity?.showLoading(isDisplayed)
    }

    override fun showError(resId: Int) {
        baseActivity?.showError(resId) ?: Toast.makeText(context, getString(resId), Toast.LENGTH_SHORT).show()
    }
}