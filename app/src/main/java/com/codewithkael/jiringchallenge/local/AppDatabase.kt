package com.codewithkael.jiringchallenge.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codewithkael.jiringchallenge.local.models.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UsersDao
}