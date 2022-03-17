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
import com.conamobile.egzamenvisacard.Room.UserModel
import com.conamobile.egzamenvisacard.memory.MySharedPrefarance

//for room
class UserAdapter(
    var context: Context,
    list: List<UserModel>

) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var list: List<UserModel> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardNumber.text = list[position].cardNumber
        holder.cardUserName.text = list[position].cardUserName
        holder.cardDate.text = "${list[position].cardDateMonth}/${list[position].cardDateYear}"
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardNumber: AppCompatTextView = itemView.findViewById(R.id.itemCardNumber)
        var cardUserName: TextView = itemView.findViewById(R.id.itemUserName)
        var cardDate: TextView = itemView.findViewById(R.id.itemDate)

    }

}
