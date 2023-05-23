package com.soar.sdkdemo.base

import android.app.Application
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent

/**
 * NAME：YONG_
 * Created at: 2023/3/23 16
 * Describe:
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private var lifecycle: LifecycleProvider<ActivityEvent>? = null

    var whichChild: ObservableInt = ObservableInt()

    /**
     * 注入RxLifecycle生命周期
     */
    open fun injectLifecycleProvider(lifecycle: LifecycleProvider<ActivityEvent>) {
        this.lifecycle = lifecycle
    }

    open fun getLifecycleProvider(): LifecycleProvider<ActivityEvent>? {
        return lifecycle
    }

    /**
     * 若有引用到Activity,比如Context等
     *
     * 在Activity销毁时,ViewModel重写此方法释放引用
     */
    open fun onDestroy(){

    }
}