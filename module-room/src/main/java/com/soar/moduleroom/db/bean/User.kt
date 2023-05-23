package com.soar.moduleroom.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * NAME：YONG_
 * Created at: 2023/5/19 16
 * Describe:
 *
 *
 * 注意：
 * 1.
 * val account: String？这样可以定义字段是可空的NOT NULL
 * val account: String 则表示NOT NULL=true
 *
 * 2.
 * room框架规定数据库必须要定义主键，否则room框架报错
 */
@Entity
data class User(val account: String,val pwd:String){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
