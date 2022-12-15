package com.mochire.tech.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.mochire.tech.database.dao.UserDao
import com.mochire.tech.database.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val userDao: UserDao) : ViewModel() {

    var allUsers = listOf<User>()
    var user = User(0, name = "", gender = "", age = 0, phoneNumber = 0, conditions = "")

    fun getUser(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            user = userDao.getUser(id)
        }
    }

    fun createUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.createUser(user)
        }
    }

    fun getAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
           allUsers = userDao.getAllUsers()
        }
    }


}

class ProfileViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(userDao) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}