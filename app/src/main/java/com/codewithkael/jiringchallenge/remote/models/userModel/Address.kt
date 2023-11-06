package com.codewithkael.jiringchallenge.remote.models.userModel

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)