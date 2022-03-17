package com.conamobile.egzamenvisacard.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.conamobile.egzamenvisacard.R
import com.conamobile.egzamenvisacard.model.CardModel
import java.util.ArrayList

class RetrofitGetAdapter(private val context: Context, private val lists: ArrayList<CardModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val list = lists[position]

        if (holder is MyViewHolder) {

            holder.apply {
                itemCardNumber.text = list.cardNumber
                itemUserName.text = list.cardUserName
                itemDate.text = "${list.cardDateMonth}/${list.cardDateYear}"

            }

        }
    }


    override fun getItemCount(): Int {
        return lists.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemCardNumber: AppCompatTextView = view.findViewById(R.id.itemCardNumber)
        val itemUserName: TextView = view.findViewById(R.id.itemUserName)
        val itemDate: TextView = view.findViewById(R.id.itemDate)
    }
}