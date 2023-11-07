package com.codewithkael.jiringchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codewithkael.jiringchallenge.data.local.models.UserEntity

@Database(entities = [UserEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UsersDao
}