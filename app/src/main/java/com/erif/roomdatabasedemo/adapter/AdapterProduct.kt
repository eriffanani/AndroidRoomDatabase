package com.erif.roomdatabasedemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erif.roomdatabasedemo.R
import com.erif.roomdatabasedemo.database.product.Product
import com.erif.roomdatabasedemo.database.user.User
import com.erif.roomdatabasedemo.holder.HolderProduct
import com.erif.roomdatabasedemo.holder.HolderUser

class AdapterProduct : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderProduct(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HolderProduct)
            holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: MutableList<Product>) {
        this.list = list
        notifyItemInserted(0)
    }

}