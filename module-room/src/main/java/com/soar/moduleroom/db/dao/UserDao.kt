package com.soar.moduleroom.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.soar.moduleroom.db.bean.User

/**
 * NAME：YONG_
 * Created at: 2023/5/19 16
 * Describe:
 */
@Dao
interface UserDao {
    @Insert
    fun insertUser(user:User): Long

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user:User)

    @Query("SELECT * FROM user WHERE account = :account AND pwd = :pwd")
    fun login(account:String,pwd:String): LiveData<User?>
}