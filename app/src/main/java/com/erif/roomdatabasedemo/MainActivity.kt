package com.erif.roomdatabasedemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.CoroutinesRoom
import androidx.room.Room
import com.erif.roomdatabasedemo.adapter.AdapterProduct
import com.erif.roomdatabasedemo.adapter.AdapterUser
import com.erif.roomdatabasedemo.database.AppDatabase
import com.erif.roomdatabasedemo.database.product.Product
import com.erif.roomdatabasedemo.database.product.ProductDao
import com.erif.roomdatabasedemo.database.user.User
import com.erif.roomdatabasedemo.database.user.UserDao
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val adapterUser = AdapterUser()
    private val listUser: MutableList<User> = ArrayList()
    private lateinit var recyclerUser: RecyclerView

    private val adapterProduct = AdapterProduct()
    private val listProduct: MutableList<Product> = ArrayList()
    private lateinit var recyclerProduct: RecyclerView

    private lateinit var dbUser: UserDao
    private lateinit var dbProduct: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(
            this, AppDatabase::class.java, "database-demo"
        ).build()
        dbUser = db.userDao()
        dbProduct = db.productDao()

        val layoutManagerUser = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerProduct = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerUser = findViewById(R.id.act_main_recyclerUser)
        recyclerUser.adapter = adapterUser
        recyclerUser.layoutManager = layoutManagerUser

        recyclerProduct = findViewById(R.id.act_main_recyclerProduct)
        recyclerProduct.adapter = adapterProduct
        recyclerProduct.layoutManager = layoutManagerProduct

        setupUser()
        setupInputUser()

        setupProduct()

    }

    private fun setupUser() {
        Thread {
            dbUser.getAll().forEach {
                listUser.add(it)
            }
            adapterUser.setList(listUser)
        }.start()
    }

    private fun setupInputUser() {
        val edFirstName: EditText = findViewById(R.id.act_main_edUserFirstName)
        val edLastName: EditText = findViewById(R.id.act_main_edUserLastName)
        val btnSave: Button = findViewById(R.id.act_main_btnSaveUser)
        btnSave.setOnClickListener {
            val firstName = edFirstName.text.toString()
            val lastName = edLastName.text.toString()
            val user = User(null, firstName, lastName)
            MainScope().launch {
                withContext(Dispatchers.Default) {
                    dbUser.insert(user)
                }
                adapterUser.addItem(user)
                edFirstName.text = null
                edLastName.text = null
            }
        }
    }

    private fun setupProduct() {
        Thread {
            dbProduct.getAll().forEach {
                listProduct.add(it)
            }
            adapterProduct.setList(listProduct)
        }.start()
    }

}