package com.conamobile.egzamenvisacard

import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.conamobile.egzamenvisacard.Room.DatabaseClass
import com.conamobile.egzamenvisacard.Room.UserModel
import com.conamobile.egzamenvisacard.adapter.RetrofitGetAdapter
import com.conamobile.egzamenvisacard.model.CardModel
import com.conamobile.egzamenvisacard.networing.ApiClient
import com.conamobile.egzamenvisacard.networing.ApiService
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCardActivity : AppCompatActivity() {

    lateinit var backBtn: ImageView
    lateinit var addCardNumberEditText: TextInputEditText
    lateinit var addCardMonth: TextInputEditText
    lateinit var addCardYear: TextInputEditText
    lateinit var addCardCvv: TextInputEditText
    lateinit var addCardUserName: TextInputEditText
    lateinit var addNewCardButton: CircularRevealCardView
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        initViews()
    }

    private fun initViews() {
        backBtn = findViewById(R.id.backBtn)
        addCardNumberEditText = findViewById(R.id.addCardNumberEditText)
        addCardMonth = findViewById(R.id.addCardMonth)
        addCardYear = findViewById(R.id.addCardYear)
        addCardCvv = findViewById(R.id.addCardCvv)
        addCardUserName = findViewById(R.id.addCardUserName)
        addNewCardButton = findViewById(R.id.addNewCardButton)
        apiService = ApiClient.createService(ApiService::class.java)

        backBtn.setOnClickListener {
            finish()
        }

        addNewCardButton.setOnClickListener {
            if (addCardNumberEditText.text.toString() != ""
                && addCardMonth.text.toString() != ""
                && addCardYear.text.toString() != ""
                && addCardCvv.text.toString() != ""
                && addCardUserName.text.toString() != ""
            ) {
                saveData()
                postUserNetwork()
            }
        }

    }


    private fun postUserNetwork() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
        val user =
            CardModel(
                0, "VISA", addCardNumberEditText.text.toString(),
                addCardUserName.text.toString(),
                addCardMonth.text.toString(),
                addCardYear.text.toString(),
                addCardCvv.text.toString(),
                "$3,480"
            )
        apiService.putActivity(user).enqueue(object : Callback<List<CardModel>> {
            override fun onResponse(
                call: Call<List<CardModel>>,
                response: Response<List<CardModel>>
            ) {

            }

            override fun onFailure(call: Call<List<CardModel>>, t: Throwable) {
            }
        })
    }

    private fun saveData() {
        val model = UserModel()

        model.cardName = "VISA"
        model.cardNumber = addCardNumberEditText.text.toString()
        model.cardUserName = addCardUserName.text.toString()
        model.cardDateMonth = addCardMonth.text.toString()
        model.cardDateYear = addCardYear.text.toString()
        model.cardCvv = addCardCvv.text.toString()
        model.cardBalance = "$4,323"

        DatabaseClass.getDatabase(applicationContext)!!.getDao().insertAllData(model)

        Toast.makeText(this, "Data Successfully Saved", Toast.LENGTH_SHORT).show()
        MainActivity.getInfo()

        finish()

    }

}