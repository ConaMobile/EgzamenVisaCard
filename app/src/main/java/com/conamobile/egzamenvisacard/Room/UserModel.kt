package com.conamobile.egzamenvisacard.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//for room
@Entity(tableName = "user")
class UserModel {
    //Primary key
    @PrimaryKey(autoGenerate = true)
    var key = 0

    var cardName: String? = null
    var cardNumber: String? = null
    var cardUserName: String? = null
    var cardDateMonth: String? = null
    var cardDateYear: String? = null
    var cardCvv: String? = null
    var cardBalance: String? = null
}

