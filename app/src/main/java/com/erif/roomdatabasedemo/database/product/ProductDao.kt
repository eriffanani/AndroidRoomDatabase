package com.erif.roomdatabasedemo.database.product

import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM table_product")
    fun getAllProduct(): MutableList<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("DELETE FROM table_product WHERE id = :productId")
    suspend fun delete(productId: Int)

    @Query("SELECT * FROM table_product ORDER BY id DESC LIMIT 1")
    fun getLastProduct(): Product

}