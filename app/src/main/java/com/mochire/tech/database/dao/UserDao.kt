package com.mochire.tech.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mochire.tech.database.entity.User

@Dao
interface UserDao {

    @Insert
    fun createUser(user: User)

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUser(id: Int): User

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): List<User>
}