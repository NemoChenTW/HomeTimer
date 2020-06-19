package com.nemochen.hometimer.model.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountdownEntity::class], version = 1)
abstract class CountdownDatabase: RoomDatabase() {

    abstract fun countdownDAO(): CountdownDAO

    companion object {
        @Volatile private var INSTANCE: CountdownDatabase? = null

        fun getInstance(context: Context): CountdownDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                                CountdownDatabase::class.java,
                            "countdown.db").build()
    }
}