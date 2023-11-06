package com.codewithkael.jiringchallenge.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codewithkael.jiringchallenge.local.models.UserEntity

@Dao
interface UsersDao {
    @Query("SELECT * FROM users limit 1")
    suspend fun getUserByUsername() : UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
}