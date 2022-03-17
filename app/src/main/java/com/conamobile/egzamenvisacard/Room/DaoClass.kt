package com.conamobile.egzamenvisacard.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//for room
@Dao
interface DaoClass {
    //put data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(model: UserModel?)

    //get All Data
    @Query("select * from  user")
    fun getAllData(): List<UserModel?>?

//    //DELETE DATA
//    @Query("delete from user where `key`= :id")
//    fun deleteData(id: Int)

    //Update Data
//    @Query("update user SET name= :name ,address =:address, phone =:phone where `key`= :key")
//    fun updateData(name: String?, phone: String?, address: String?, key: Int)
}