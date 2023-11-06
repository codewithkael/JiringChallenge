package com.codewithkael.jiringchallenge.di

import android.content.Context
import androidx.room.Room
import com.codewithkael.jiringchallenge.local.AppDatabase
import com.codewithkael.jiringchallenge.local.UsersDao
import com.codewithkael.jiringchallenge.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataBaseModule {

    @Provides
    fun provideAppDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()
    }

    @Provides
    fun provideUsersDao(db: AppDatabase): UsersDao = db.userDao()
}