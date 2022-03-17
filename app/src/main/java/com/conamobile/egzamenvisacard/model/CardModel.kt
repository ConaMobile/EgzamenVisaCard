package com.conamobile.egzamenvisacard.model

data class CardModel(
    var id: Int,
    var cardName: String,
    var cardNumber: String,
    var cardUserName: String,
    var cardDateMonth: String,
    var cardDateYear: String,
    var cardCvv: String,
    var cardBalance: String
)
