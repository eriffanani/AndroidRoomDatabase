package com.erif.roomdatabasedemo.database.product

class ProductRepository(private val productDao: ProductDao) {

    suspend fun insert(product: Product) {
        productDao.insert(product)
    }

    fun getAllProduct(): MutableList<Product> {
        return productDao.getAllProduct()
    }

    suspend fun deleteProduct(productId: Int) {
        productDao.delete(productId)
    }

    fun getLastProduct(): Product {
        return productDao.getLastProduct()
    }

}