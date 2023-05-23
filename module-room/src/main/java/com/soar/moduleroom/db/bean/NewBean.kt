package com.soar.moduleroom.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * NAME：YONG_
 * Created at: 2023/5/19 16
 * Describe:
 */
@Entity
data class NewBean(val name: String){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
