package com.erif.roomdatabasedemo.database.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Insert
    fun insert(vararg product: Product)

}