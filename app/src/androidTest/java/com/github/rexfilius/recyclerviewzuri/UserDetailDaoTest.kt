package com.github.rexfilius.recyclerviewzuri;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.github.rexfilius.recyclerviewzuri.database.UserDetail
import com.github.rexfilius.recyclerviewzuri.database.UserDetailDao
import com.github.rexfilius.recyclerviewzuri.database.UserDetailDatabase

import org.junit.Rule;
import org.junit.runner.RunWith;

import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UserDetailDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userDetailDatabase: UserDetailDatabase
    private lateinit var userDetailDao: UserDetailDao

    @Before
    fun createDatabase() {
        userDetailDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDetailDatabase::class.java
        ).allowMainThreadQueries().build()
        userDetailDao = userDetailDatabase.userDetailDao()
    }

    @After
    fun closeDatabase() {
        userDetailDatabase.close()
    }

    @Test
    fun insertUserDetail() = runBlockingTest {
        val userDetail = UserDetail("jane@one.com", "jane")
        userDetailDao.insertUserDetail(userDetail)

        val allUserDetails = userDetailDao.getAllUserDetails().getOrAwaitValue()

        assertThat(allUserDetails).contains(userDetail)
    }

    @Test
    fun deleteUserDetail() = runBlockingTest {
        val userDetail = UserDetail("jane@one.com", "jane")

        userDetailDao.insertUserDetail(userDetail)
        userDetailDao.deleteUserDetail(userDetail)

        val allUserDetails = userDetailDao.getAllUserDetails().getOrAwaitValue()
        assertThat(allUserDetails).doesNotContain(userDetail)
    }

    @Test
    fun findUserByEmail() = runBlockingTest {
        val userDetail = UserDetail("jane@one.com", "jane")
        userDetailDao.insertUserDetail(userDetail)

        val allUserDetails = userDetailDao.getAllUserDetails().getOrAwaitValue()
        val email = userDetailDao.findUserByEmail(userDetail.email)

        assertThat(allUserDetails).contains(email)
    }

    @Test
    fun getAllUserDetails() = runBlockingTest {
        val userDetail1 = UserDetail("john@one.com", "john")
        val userDetail2 = UserDetail("jude@one.com", "jude")
        val userDetail3 = UserDetail("jake@one.com", "jake")

        userDetailDao.insertUserDetail(userDetail1)
        userDetailDao.insertUserDetail(userDetail2)
        userDetailDao.insertUserDetail(userDetail3)

        val allUserDetails = userDetailDao.getAllUserDetails().getOrAwaitValue()

        assertThat(allUserDetails).containsExactly(userDetail1, userDetail2, userDetail3)
    }


}
