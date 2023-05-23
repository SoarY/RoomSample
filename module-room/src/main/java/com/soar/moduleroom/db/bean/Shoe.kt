package com.soar.moduleroom.db.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 鞋表
 */
@Entity(tableName = "NewShoe")
data class Shoe(
    @ColumnInfo(name = "shoe_name") val name: String
    , @ColumnInfo(name = "shoe_imgUrl") val imageUrl: String,val newColumn: String?=null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}