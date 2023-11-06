package com.codewithkael.jiringchallenge.remote.models.userModel

data class UserModel(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)