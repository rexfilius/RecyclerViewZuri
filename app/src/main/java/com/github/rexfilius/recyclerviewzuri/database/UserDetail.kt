package com.github.rexfilius.recyclerviewzuri.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDetail(
    @PrimaryKey
    val email: String,

    val password: String,
)
