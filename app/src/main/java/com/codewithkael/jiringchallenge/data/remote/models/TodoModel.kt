package com.codewithkael.jiringchallenge.data.remote.models

data class TodoModel(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)