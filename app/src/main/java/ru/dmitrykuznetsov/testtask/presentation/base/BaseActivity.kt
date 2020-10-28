package ru.dmitrykuznetsov.testtask.presentation.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ru.dmitrykuznetsov.testtask.presentation.base.mvp.MvpView

@SuppressLint("Registered")
abstract class BaseActivity<B : ViewDataBinding>: AppCompatActivity(), MvpView {

    /** Идентификатор разметки */
    protected abstract val layoutId: Int
        @LayoutRes get

    /** Биндинг разметки */
    protected lateinit var binding: B
        private set

    /** Диалог загрузки */
    private lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        progressDialog = createProgressDialog(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun showLoading(isDisplayed: Boolean) {
        progressDialog.dismiss()
        if (isDisplayed) {
            progressDialog.show()
        }
    }

    private fun createProgressDialog(context: Context, message: String? = null): ProgressDialog =
            ProgressDialog(context).apply {
                setMessage(message)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                isIndeterminate = true
            }

    override fun showError(@StringRes resId: Int) =
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
}
