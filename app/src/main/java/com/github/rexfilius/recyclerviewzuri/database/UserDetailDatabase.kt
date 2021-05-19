package com.github.rexfilius.recyclerviewzuri.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserDetail::class], version = 1, exportSchema = false)
abstract class UserDetailDatabase : RoomDatabase() {

    abstract fun userDetailDao(): UserDetailDao

    companion object {
        @Volatile
        private var instance: UserDetailDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, UserDetailDatabase::class.java, "userdetail.db")
                .build()
    }

}

