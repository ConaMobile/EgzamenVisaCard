package com.conamobile.egzamenvisacard.memory

import android.content.Context

class MySharedPrefarance(context: Context) {
    private val pref = context.getSharedPreferences("nimadir", Context.MODE_PRIVATE)


    fun isSavedCount(isSavedCount: Int) {
        val editor = pref.edit()
        editor.putInt("isSavedCount", isSavedCount)
        editor.apply()
    }

    fun getSavedCount(): Int? {
        return pref.getInt("isSavedCount", 0)
    }

    /////////////////////////////

    fun isSavedElement(
        cardNumber: String,
        cardUserName: String,
        cardDateMonth: String,
        cardDateYear: String,
        cardCvv: String,
    ) {
        val editor = pref.edit()
        editor.putString("cardNumber", cardNumber)
        editor.putString("cardUserName", cardUserName)
        editor.putString("cardDateMonth", cardDateMonth)
        editor.putString("cardDateYear", cardDateYear)
        editor.putString("cardCvv", cardCvv)
        editor.apply()
    }

    fun getSavedElement(): String? {
        return pref.getString("cardNumber", "0")
        return pref.getString("cardUserName", "username")
        return pref.getString("cardDateMonth", "0")
        return pref.getString("cardDateYear", "0")
        return pref.getString("cardCvv", "0")
    }

}