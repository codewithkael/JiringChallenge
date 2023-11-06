package com.codewithkael.jiringchallenge.utils

sealed class ResponseWrapper<out T> {
    data class Success<T>(val body: T) : ResponseWrapper<T>()
    data class Failure(val exception: Exception) : ResponseWrapper<Nothing>()
}