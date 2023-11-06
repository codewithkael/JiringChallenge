package com.codewithkael.jiringchallenge.utils

import com.codewithkael.jiringchallenge.local.models.UserEntity
import com.codewithkael.jiringchallenge.remote.models.userModel.UserModel

fun UserModel.toEntity(): UserEntity {
    return UserEntity(username = this.username, userId = this.id, name = this.name)
}