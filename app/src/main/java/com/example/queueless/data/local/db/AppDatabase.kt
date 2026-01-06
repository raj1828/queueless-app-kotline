package com.example.queueless.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.queueless.data.local.dao.AuthDao
import com.example.queueless.data.local.entity.AuthEntity

@Database(
    entities = [AuthEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun authDao(): AuthDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "queueless_db"
                ).build().also { INSTANCE = it }
            }
    }
}
