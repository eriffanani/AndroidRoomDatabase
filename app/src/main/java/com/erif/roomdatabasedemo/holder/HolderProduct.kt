package com.erif.roomdatabasedemo.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erif.roomdatabasedemo.R
import com.erif.roomdatabasedemo.database.product.Product

class HolderProduct(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val txtTitle: TextView = itemView.findViewById(R.id.item_product_txtTitle)
    private val txtPrice: TextView = itemView.findViewById(R.id.item_product_txtPrice)

    fun bind(item: Product) {
        txtTitle.text = item.title
        val price = "Rp. ${item.price}"
        txtPrice.text = price
    }

}