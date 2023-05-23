package com.soar.sdkdemo.base

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.trello.navi2.component.support.NaviAppCompatActivity
import com.trello.rxlifecycle2.navi.NaviLifecycle
import java.lang.reflect.ParameterizedType

/**
 * NAME：YONG_
 * Created at: 2018/12/7 11
 * Describe:
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : NaviAppCompatActivity() {

    val TAG = this.javaClass.simpleName

    lateinit var context: Activity
    lateinit var mDinding: V
    lateinit var mViewModel: VM

    abstract fun initContentView(savedInstanceState: Bundle?): Int

    abstract fun initVariableId():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?){
        context=this

        mDinding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        mViewModel=getViewModel()
        mDinding.setVariable(initVariableId(),mViewModel)
        mViewModel.injectLifecycleProvider(NaviLifecycle.createActivityLifecycleProvider(this))
    }

    private fun getViewModel(): VM {
        val type = javaClass.genericSuperclass
        val modelClass: Class<BaseViewModel>
        modelClass =
            if (type is ParameterizedType) type.actualTypeArguments[1] as Class<BaseViewModel> else BaseViewModel::class.java //如果没有指定泛型参数，则默认使用BaseViewModel
        return ViewModelProvider(this).get(modelClass) as VM
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.onDestroy()
        mDinding.unbind()
    }
}