package com.example.queueless.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth")
data class AuthEntity(
    @PrimaryKey val id: Int = 1, // single row only
    val accessToken: String,
    val refreshToken: String,
    val isAuthenticated: Boolean
)
