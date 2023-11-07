package com.codewithkael.jiringchallenge.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Long?=null,
    val username:String?=null,
    val userId:Int?=null,
    val name:String?=null
)