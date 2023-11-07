package com.codewithkael.jiringchallenge.data.local

import com.codewithkael.jiringchallenge.data.local.models.UserEntity
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val usersDao: UsersDao
) {

    suspend fun insertUser(user: UserEntity) {
        usersDao.insertUser(user)
    }

    suspend fun getCurrentUser(): UserEntity?{
        return usersDao.getUserByUsername()
    }

    suspend fun clearUserDb(){
        usersDao.clearDb()
    }

}