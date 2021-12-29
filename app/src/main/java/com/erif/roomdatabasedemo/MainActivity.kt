package com.erif.roomdatabasedemo

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erif.roomdatabasedemo.adapter.AdapterProduct
import com.erif.roomdatabasedemo.adapter.AdapterUser
import com.erif.roomdatabasedemo.database.product.Product
import com.erif.roomdatabasedemo.database.user.User

class MainActivity : AppCompatActivity(), AdapterProduct.Callback {

    private val adapterUser = AdapterUser()
    private lateinit var recyclerUser: RecyclerView

    private var edFirstName: EditText? = null
    private var edLastName: EditText? = null

    private var edProductTitle: EditText? = null
    private var edProductPrice: EditText? = null

    private val adapterProduct = AdapterProduct(this)
    private lateinit var recyclerProduct: RecyclerView

    private var viewModel: VMMainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManagerUser = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerProduct = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerUser = findViewById(R.id.act_main_recyclerUser)
        recyclerUser.adapter = adapterUser
        recyclerUser.layoutManager = layoutManagerUser

        recyclerProduct = findViewById(R.id.act_main_recyclerProduct)
        recyclerProduct.adapter = adapterProduct
        recyclerProduct.layoutManager = layoutManagerProduct

        viewModel = VMMainActivity(this)

        setupUser()
        setupProduct()

        setupInputUser()
        setupInputProduct()

        viewModel?.addUserMutable()?.observe(this, {
            adapterUser.addItem(it)
        })

        viewModel?.addProductMutable()?.observe(this, {
            adapterProduct.addItem(it)
        })

        viewModel?.deleteProductMutable()?.observe(this, {
            adapterProduct.delete(it)
        })

    }

    private fun setupUser() {
        viewModel?.readUser(adapterUser)
    }

    private fun setupInputUser() {
        edFirstName = findViewById(R.id.act_main_edUserFirstName)
        edLastName = findViewById(R.id.act_main_edUserLastName)
        val btnSave: Button = findViewById(R.id.act_main_btnSaveUser)
        btnSave.setOnClickListener {
            val firstName = edFirstName?.text.toString()
            val lastName = edLastName?.text.toString()
            viewModel?.addUser(
                User(0, firstName, lastName)
            )
            normalize()
        }
    }

    private fun setupProduct() {
        viewModel?.readProduct(adapterProduct)
    }

    private fun setupInputProduct() {
        edProductTitle = findViewById(R.id.act_main_edProductTitle)
        edProductPrice = findViewById(R.id.act_main_edProductPrice)
        val btnSave: Button = findViewById(R.id.act_main_btnSaveProduct)
        btnSave.setOnClickListener {
            val productTitle = edProductTitle?.text.toString()
            val productPrice = edProductPrice?.text.toString()
            viewModel?.addProduct(
                Product(0, productTitle, productPrice)
            )
            normalize()
        }
    }

    override fun onClickItemProduct(product: Product) {
        viewModel?.deleteProduct(product)
    }

    private fun normalize() {
        edFirstName?.setText("")
        edLastName?.setText("")
        edProductTitle?.setText("")
        edProductPrice?.setText("")
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken ?: return, 0)
    }

}