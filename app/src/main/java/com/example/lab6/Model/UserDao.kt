package com.example.lab6.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)
    @Query("SELECT * FROM User")
    fun getAllUsers():List<User>
}