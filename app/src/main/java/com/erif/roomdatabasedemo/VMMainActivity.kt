package com.erif.roomdatabasedemo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erif.roomdatabasedemo.adapter.AdapterProduct
import com.erif.roomdatabasedemo.adapter.AdapterUser
import com.erif.roomdatabasedemo.database.product.Product
import com.erif.roomdatabasedemo.database.product.ProductDatabase
import com.erif.roomdatabasedemo.database.product.ProductRepository
import com.erif.roomdatabasedemo.database.user.User
import com.erif.roomdatabasedemo.database.user.UserDatabase
import com.erif.roomdatabasedemo.database.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VMMainActivity(context: Context) : ViewModel() {

    private var userRepository: UserRepository? = null
    private val listUser: MutableList<User> = ArrayList()
    private val mutableUser: MutableLiveData<User> = MutableLiveData()

    private val listProduct: MutableList<Product> = ArrayList()
    private var productRepository: ProductRepository? = null
    private val mutableProduct: MutableLiveData<Product> = MutableLiveData()
    private val mutableProductDelete: MutableLiveData<Product> = MutableLiveData()

    init {
        userRepository = UserRepository(
            UserDatabase.getDatabase(context).userDao()
        )
        productRepository = ProductRepository(
            ProductDatabase.getDatabase(context).productDao()
        )
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository?.addUser(user)
            if (listUser.size > 0) {
                val lastId = listUser[listUser.size - 1].id
                user.id = lastId + 1
            }
            mutableUser.postValue(user)
        }
    }

    fun addUserMutable(): MutableLiveData<User> {
        return mutableUser
    }

    fun readUser(adapter: AdapterUser) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository?.readAllData()?.forEach {
                listUser.add(it)
            }
            adapter.setList(listUser)
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository?.insert(product)

            val lastProduct = productRepository?.getLastProduct()
            lastProduct?.let {
                product.id = it.id
                mutableProduct.postValue(product)
            }
        }
    }

    fun addProductMutable(): MutableLiveData<Product> {
        return mutableProduct
    }

    fun deleteProductMutable(): MutableLiveData<Product> {
        return mutableProductDelete
    }

    fun readProduct(adapter: AdapterProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository?.getAllProduct()?.forEach {
                listProduct.add(it)
            }
            adapter.setList(listProduct)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository?.deleteProduct(product.id)
            mutableProductDelete.postValue(product)
        }
    }

    /*private fun log(message: String) {
        Log.d("RoomDatabase", message)
    }*/

}