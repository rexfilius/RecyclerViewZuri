package com.github.rexfilius.recyclerviewzuri.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDetailDao {

    @Insert
    fun insertUserDetail(userDetail: UserDetail)

    @Delete
    fun deleteUserDetail(userDetail: UserDetail)

    @Update
    fun updateUserDetail(userDetail: UserDetail)

    @Query("SELECT * FROM userdetail")
    fun getAllUserDetails(): LiveData<List<UserDetail>>

    @Query("SELECT * FROM userdetail WHERE email = :emailAddress")
    fun findUserByEmail(emailAddress: String): UserDetail
}