package com.erif.roomdatabasedemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erif.roomdatabasedemo.R
import com.erif.roomdatabasedemo.database.user.User
import com.erif.roomdatabasedemo.holder.HolderUser

class AdapterUser : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderUser(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HolderUser)
            holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: MutableList<User>) {
        this.list = list
        notifyItemInserted(0)
    }

    fun addItem(user: User) {
        list.add(user)
        notifyItemInserted(itemCount)
    }

}