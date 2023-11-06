package com.codewithkael.jiringchallenge.remote

import com.codewithkael.jiringchallenge.remote.models.TodoModel
import com.codewithkael.jiringchallenge.remote.models.userModel.UserModel
import com.codewithkael.jiringchallenge.utils.ResponseWrapper
import java.lang.Exception
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUserByUserName(username:String): ResponseWrapper<List<UserModel>>
    {
        return try {
            ResponseWrapper.Success(apiService.getUserByUsername(username))
        }catch (e:Exception){
            ResponseWrapper.Failure(e)
        }
    }

    suspend fun getTodoList(userId:Int): ResponseWrapper<List<TodoModel>>
    {
        return try {
            ResponseWrapper.Success(apiService.getTodosByUserId(userId))
        }catch (e:Exception){
            ResponseWrapper.Failure(e)
        }
    }
}