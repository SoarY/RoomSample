package com.soar.moduleroom.db.dao

import androidx.room.*
import com.soar.moduleroom.db.bean.NewBean

/**
 * NAME：YONG_
 * Created at: 2023/5/19 16
 * Describe:
 */
@Dao
interface NewBeanDao {
    @Insert
    fun insertUser(bean:NewBean): Long
}