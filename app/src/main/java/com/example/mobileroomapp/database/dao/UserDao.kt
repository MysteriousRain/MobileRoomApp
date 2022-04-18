package com.example.mobileroomapp.database.dao

import androidx.room.*
import com.example.mobileroomapp.database.model.UserModel


@Dao
interface UserDao {
    @Insert
    fun insertAll(user: UserModel)

    @Delete
    fun delete(user: UserModel)

    @Update
    fun updateUser(user: UserModel)

    @Query("SELECT * FROM UserModel")
    fun getAll(): List<UserModel>

    @Query("Delete from UserModel")
    fun deleteAll()
}