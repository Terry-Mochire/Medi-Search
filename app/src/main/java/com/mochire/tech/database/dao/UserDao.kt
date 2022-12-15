package com.mochire.tech.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mochire.tech.database.entity.User

@Dao
interface UserDao {

    @Insert
    fun createUser(user: User)

    @Query("SELECT * FROM user_table WHERE name = :name")
    fun getUser(name: String): User
}