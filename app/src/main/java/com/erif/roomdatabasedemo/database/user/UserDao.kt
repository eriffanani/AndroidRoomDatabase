package com.erif.roomdatabasedemo.database.user

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM table_user")
    fun getAll(): List<User>

    @Query("SELECT * FROM table_user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM table_user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}