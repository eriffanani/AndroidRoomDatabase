package com.erif.roomdatabasedemo.database.user

class UserRepository(private val userDao: UserDao) {

    fun readAllData(): List<User> {
        return userDao.getAll()
    }

    suspend fun addUser(user: User) {
        userDao.insert(user)
    }

}