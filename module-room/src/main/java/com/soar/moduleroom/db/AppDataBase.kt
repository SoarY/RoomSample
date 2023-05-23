package com.soar.moduleroom.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.soar.moduleroom.db.bean.NewBean
import com.soar.moduleroom.db.bean.Shoe
import com.soar.moduleroom.db.bean.User
import com.soar.moduleroom.db.dao.NewBeanDao
import com.soar.moduleroom.db.dao.ShoeDao
import com.soar.moduleroom.db.dao.UserDao

/**
 * NAME：YONG_
 * Created at: 2023/5/19 16
 * Describe:
 */
@Database(entities = [User::class, Shoe::class,NewBean::class],version = 1,/*version = 1,*/exportSchema = true,/*autoMigrations = [
    AutoMigration (
        from = 1,
        to = 2,
        spec = AppDataBase.AutoMigration1_2::class
    )]*/)
abstract class AppDataBase : RoomDatabase(){

    // 得到UserDao
    abstract fun userDao(): UserDao

    abstract fun shoeDao(): ShoeDao

    abstract fun newBeanDao(): NewBeanDao


    /**
     * Room 中的数据库自动迁移功能
     * https://juejin.cn/post/6989434170598653989
     * 数据库自动升级时：以下情况下需要特殊标识
     * 删除或重命名表。
     * 删除或重命名列。
     * @DeleteTable(tableName)
     * @RenameTable(fromTableName, toTableName)
     * @DeleteColumn(tableName, columnName)
     * @RenameColumn(tableName, fromColumnName, toColumnName)
     */
    @RenameTable(fromTableName = "Shoe", toTableName = "NewShoe")
    class AutoMigration1_2: AutoMigrationSpec {
        override fun onPostMigrate(db: SupportSQLiteDatabase) {
            super.onPostMigrate(db)
            //自动升级后可自定义操作
        }
    }

    companion object{
        @Volatile
        private var instance:AppDataBase? = null

        fun getInstance(context: Context):AppDataBase{
            return instance?: synchronized(this){
                instance?:buildDataBase(context)
                    .also {
                        instance = it
                    }
            }
        }

        private fun buildDataBase(context: Context):AppDataBase{
            return Room
                .databaseBuilder(context,AppDataBase::class.java,"room-database")
                //.fallbackToDestructiveMigration()//数据库更新时删除数据重新创建
                //.addMigrations(MIGRATION_1_2)
                .build()
        }

        private val MIGRATION_1_2 = object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE 'device' ('id'  TEXT NOT NULL,'location' TEXT,'deviceName' TEXT,'deviceType' TEXT,PRIMARY KEY ('id')) ");
            }
        }
    }

}