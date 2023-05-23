package com.soar.moduleroom.db.repository

import androidx.lifecycle.LiveData
import com.soar.moduleroom.db.bean.User
import com.soar.moduleroom.db.dao.UserDao

/**
 * NAME：YONG_
 * Created at: 2023/5/19 17
 * Describe:
 */
class UserRepository private constructor(private val userDao: UserDao)  {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository =
            instance ?: synchronized(this) {
                instance
                    ?: UserRepository(userDao).also {
                        instance = it
                    }
            }

    }

    /**
     * 登录用户
     */
    fun login(account: String, pwd: String): LiveData<User?>
            = userDao.login(account,pwd)

    /**
     * 注册一个用户
     */
    fun register(account: String, pwd: String):Long = userDao.insertUser(User(account,pwd))
}