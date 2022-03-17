package com.conamobile.egzamenvisacard

import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.conamobile.egzamenvisacard.Room.DatabaseClass
import com.conamobile.egzamenvisacard.Room.UserModel
import com.conamobile.egzamenvisacard.adapter.RetrofitGetAdapter
import com.conamobile.egzamenvisacard.adapter.UserAdapter
import com.conamobile.egzamenvisacard.memory.MySharedPrefarance
import com.conamobile.egzamenvisacard.model.CardModel
import com.conamobile.egzamenvisacard.networing.ApiClient
import com.conamobile.egzamenvisacard.networing.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("StaticFieldLeak")
private lateinit var adapter: RetrofitGetAdapter
lateinit var apiService: ApiService
private lateinit var list: ArrayList<CardModel>
private var list2: List<UserModel>? = null

@SuppressLint("StaticFieldLeak")
private lateinit var progressBar: ProgressBar

class MainActivity : AppCompatActivity() {

    lateinit var cardAdd: ImageView
    var listCard = ArrayList<CardModel>()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //delete night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    private fun initViews() {
        list2 = ArrayList()
        apiService = ApiClient.createService(ApiService::class.java)
        cardAdd = findViewById(R.id.cardAdd)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        list = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = RetrofitGetAdapter(this, list)
        recyclerView.adapter = adapter

        if (isInternetAvailable()) {
            getInfo()
            Toast.makeText(this, "Load Service", Toast.LENGTH_SHORT).show()
        } else
            data

        cardAdd.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }

    }

    companion object {
        fun getInfo() {
            apiService.getActivity().enqueue(object : Callback<List<CardModel>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<CardModel>>,
                    response: Response<List<CardModel>>
                ) {
                    Log.d("@@@", response.body().toString())
                    if (response.body() != null) {
                        list.clear()
                        list.addAll(response.body()!!)
                        adapter.notifyDataSetChanged()
                        progressBar.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<List<CardModel>>, t: Throwable) {
                    Log.d("@@@error", t.localizedMessage)
                    progressBar.visibility = View.GONE
                }
            })
        }
    }

    private val data: Unit
        get() {
            list2 = DatabaseClass.getDatabase(applicationContext)!!.getDao()
                .getAllData() as List<UserModel>?
            recyclerView.adapter =
                UserAdapter(this, list2!!)
            Toast.makeText(this, "Load Local Data", Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.GONE
        }

    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return infoMobile!!.isConnected || infoWifi!!.isConnected
    }

}