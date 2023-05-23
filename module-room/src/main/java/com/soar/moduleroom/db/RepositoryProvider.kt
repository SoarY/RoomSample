package com.soar.moduleroom.db

import android.content.Context
import com.soar.moduleroom.db.repository.ShoeRepository
import com.soar.moduleroom.db.repository.UserRepository

/**
 * NAME：YONG_
 * Created at: 2023/5/19 17
 * Describe:
 */
object RepositoryProvider {

    /**
     * 得到用户仓库
     */
    fun providerUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(AppDataBase.getInstance(context).userDao())
    }

    fun providerShoeRepository(context: Context): ShoeRepository {
        return ShoeRepository.getInstance(AppDataBase.getInstance(context).shoeDao())
    }
}