package com.codewithkael.jiringchallenge.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Long?=null,
    val username:String,
    val userId:Int,
    val name:String
)