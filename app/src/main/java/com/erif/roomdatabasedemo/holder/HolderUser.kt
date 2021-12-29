package com.erif.roomdatabasedemo.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erif.roomdatabasedemo.R
import com.erif.roomdatabasedemo.database.user.User

class HolderUser(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val txtFirstName: TextView = itemView.findViewById(R.id.item_user_txtFirstName)
    private val txtLastName: TextView = itemView.findViewById(R.id.item_user_txtLastName)

    fun bind(item: User) {
        txtFirstName.text = item.firstName
        txtLastName.text = item.lastName
    }

}