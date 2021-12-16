package com.erif.roomdatabasedemo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erif.roomdatabasedemo.database.product.Product
import com.erif.roomdatabasedemo.database.product.ProductDao
import com.erif.roomdatabasedemo.database.user.User
import com.erif.roomdatabasedemo.database.user.UserDao

@Database(entities = [User::class, Product::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao

}