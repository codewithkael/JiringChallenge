package com.codewithkael.jiringchallenge.ui.home

import com.codewithkael.jiringchallenge.data.local.models.UserEntity
import com.codewithkael.jiringchallenge.data.remote.models.TodoModel

data class UserState (
    val userEntity: UserEntity?= UserEntity(),
    val todoList:List<TodoModel>?= listOf(),
    val isProgressBarVisible:Boolean = false
)