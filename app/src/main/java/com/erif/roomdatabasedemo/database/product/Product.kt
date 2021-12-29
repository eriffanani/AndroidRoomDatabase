package com.erif.roomdatabasedemo.database.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_product")
data class Product (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "price") val price: String?
)