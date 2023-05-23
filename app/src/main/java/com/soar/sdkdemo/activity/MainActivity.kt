package com.soar.sdkdemo.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.soar.moduleroom.db.RepositoryProvider
import com.soar.moduleroom.db.repository.ShoeRepository
import com.soar.moduleroom.db.repository.UserRepository
import com.soar.sdkdemo.R
import com.soar.sdkdemo.BR
import com.soar.sdkdemo.base.BaseActivity
import com.soar.sdkdemo.databinding.ActivityMainBinding
import com.soar.sdkdemo.vm.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    lateinit var repository:UserRepository
    lateinit var shoeRepository:ShoeRepository

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initVariableId(): Int {
        return BR.vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAssembly()
        initView()
    }

    private fun initAssembly() {
        repository = RepositoryProvider.providerUserRepository(context)
        shoeRepository = RepositoryProvider.providerShoeRepository(context)
    }

    private fun initView() {
        mDinding.btInsert.setOnClickListener {
            Thread {
                repository.register("soar", "123456")
            }.start()
        }

        mDinding.btLogin.setOnClickListener {
            val loginLiveData = repository.login("soar", "123456")
            loginLiveData.observe(this, Observer {
                it?.let {
                    Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show()
                }
            })
        }

        mDinding.btShoeInsert.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val insertShoeID = shoeRepository.insertShoe("shoe", "url")
                Toast.makeText(context, "insert成功id:$insertShoeID", Toast.LENGTH_SHORT).show()
            }
        }
    }

}