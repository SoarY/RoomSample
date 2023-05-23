package com.soar.moduleroom.db.repository

import com.soar.moduleroom.db.bean.Shoe
import com.soar.moduleroom.db.dao.ShoeDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * NAME：YONG_
 * Created at: 2023/5/19 17
 * Describe:
 */
class ShoeRepository private constructor(private val shoeDao: ShoeDao)  {

    companion object {
        @Volatile
        private var instance: ShoeRepository? = null

        fun getInstance(shoeDao: ShoeDao): ShoeRepository =
            instance ?: synchronized(this) {
                instance
                    ?: ShoeRepository(shoeDao).also {
                        instance = it
                    }
            }

    }

    suspend fun insertShoe(name: String, imageUrl: String):Long{
        return withContext(IO){
            shoeDao.insertShoe(Shoe(name,imageUrl))
        }
    }
}