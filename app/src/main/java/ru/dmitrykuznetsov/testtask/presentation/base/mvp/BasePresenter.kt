package ru.dmitrykuznetsov.testtask.presentation.base.mvp

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.dmitrykuznetsov.testtask.presentation.base.lifecycle.EmptyViewLifecycleObserver
import ru.dmitrykuznetsov.testtask.presentation.base.lifecycle.ViewLifecycleObserver
import java.lang.ref.WeakReference

/** Базовый презентер */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V>, ViewLifecycleObserver by EmptyViewLifecycleObserver() {

    /** View, которым управляет презентер */
    protected val view: V? get() = weakView.get()
    /** Слабая ссылка на View, которым управляет презентер */
    private var weakView: WeakReference<V?> = WeakReference(null)
    /** Механизм отмены реактивных потоков */
    private val disposables = CompositeDisposable()

    @CallSuper
    override fun onAttach(view: V) {
        weakView = WeakReference(view)
        view.lifecycle.addObserver(this)
        disposables.clear()
    }

    @CallSuper
    override fun onDetach() {
        view?.lifecycle?.removeObserver(this)
        weakView.clear()
        disposables.dispose()
    }

    /** Добавляет disposable в общий список presenter'a */
    protected fun Disposable.addToPresenter(): Boolean =
            disposables.add(this)
}
