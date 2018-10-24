package com.example.wuxiangyu.gank.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.wuxiangyu.gank.GankAndroidItemBean

@Database(entities = [GankAndroidItemBean::class], version = 1)
abstract class GankDatabase : RoomDatabase() {
    abstract fun ganAndroidDao(): GankAndroidDao

    companion object {
        private var instance: GankDatabase? = null
        fun of(context: Context): GankDatabase {
            if (instance == null) {
                val builder = Room.databaseBuilder(
                    context,
                    GankDatabase::class.java,
                    context.getDatabasePath("gank").absolutePath
                )
                builder.allowMainThreadQueries()
                instance = builder.build()
            }
            return instance!!
        }
    }
}