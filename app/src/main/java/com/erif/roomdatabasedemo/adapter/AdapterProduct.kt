package com.erif.roomdatabasedemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erif.roomdatabasedemo.R
import com.erif.roomdatabasedemo.database.product.Product
import com.erif.roomdatabasedemo.holder.HolderProduct

class AdapterProduct : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    constructor()

    constructor(callback: Callback?): this() {
        this.callback = callback
    }

    private var list: MutableList<Product> = ArrayList()
    private var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderProduct(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        if (holder is HolderProduct) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                callback?.onClickItemProduct(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: MutableList<Product>) {
        this.list = list
        notifyItemInserted(0)
    }

    fun addItem(product: Product) {
        list.add(product)
        notifyItemInserted(itemCount)
    }

    fun delete(product: Product) {
        val position = list.indexOf(product)
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    interface Callback {
        fun onClickItemProduct(product: Product)
    }

}