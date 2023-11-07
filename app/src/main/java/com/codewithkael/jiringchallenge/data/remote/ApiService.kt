package com.codewithkael.jiringchallenge.data.remote

import com.codewithkael.jiringchallenge.data.remote.models.TodoModel
import com.codewithkael.jiringchallenge.data.remote.models.userModel.UserModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/users")
    suspend fun getUserByUsername(@Query("username") username: String): List<UserModel>

    @GET("/todos")
    suspend fun getTodosByUserId(@Query("userId") userId: Int): List<TodoModel>
}