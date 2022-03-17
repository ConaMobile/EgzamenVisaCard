package com.conamobile.egzamenvisacard.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//for room
@Database(entities = [UserModel::class], version = 1)
internal abstract class DatabaseClass : RoomDatabase() {
    abstract fun getDao(): DaoClass

    companion object {
        private var instance: DatabaseClass? = null
        fun getDatabase(context: Context?): DatabaseClass? {
            if (instance == null) {
                synchronized(DatabaseClass::class.java) {
                    instance = Room.databaseBuilder(
                        context!!,
                        DatabaseClass::class.java, "user.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}