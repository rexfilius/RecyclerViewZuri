package com.github.rexfilius.recyclerviewzuri.database

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailRepository(context: Context) {

    private val database = UserDetailDatabase.invoke(context)

    fun insertUserDetails(userDetail: UserDetail) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                database.userDetailDao().insertUserDetail(userDetail)
            }
        }
    }

    fun findUserByEmail(userDetail: UserDetail): UserDetail {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                database.userDetailDao().findUserByEmail(userDetail.email)
            }
        }
        return userDetail
    }

}