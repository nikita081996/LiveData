package com.spartons.livedataexample.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.spartons.livedataexample.model.User

class UserRepo {

    private val userList: MutableList<User> = ArrayList()

    private val temp = MutableLiveData<List<User>>()

    fun addUserToList(name: String): List<User> {
        userList.add(User(name))
        temp.value = userList
        return temp.value as List<User>
    }

    fun addNewUserToList(name: String): LiveData<List<User>> {
        userList.add(User(name))
        temp.value = userList
        return temp
    }

    fun updateList(newValue: String) {
        temp.value?.get(0)?.username = newValue
        temp.value = temp.value
    }
}
