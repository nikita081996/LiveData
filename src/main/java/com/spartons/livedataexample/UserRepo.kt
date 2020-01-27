package com.spartons.livedataexample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class UserRepo {

    private val userList: MutableList<User> = ArrayList()
    private var user: MutableList<UserModel> = ArrayList()
    var searchResult = MutableLiveData<UserModel>()
    init {
        for (i in 1..100)
            userList.add(User("Hello user $i"))
        user.add(UserModel("Amit", 1))
        user.add(UserModel("Nikita", 2))
    }

    fun searchUserWithName(name: String): LiveData<List<User>> {
        val searchUserList = ArrayList<User>()
        for (user in userList)
            if (user.username.contains(regex = Regex(name)))
                searchUserList.add(user)
        val temp = MutableLiveData<List<User>>()
        temp.value = searchUserList
        return temp
    }

    fun searchUserWithId(id: Int): LiveData<UserModel> {

        when (id) {
            1 -> searchResult.value = user[0]
            2 -> searchResult.value = user[1]
            else -> searchResult.value = user[0]
        }
        return searchResult
    }

    fun updateUser(id: Int) {
        searchResult.value = UserModel("Sarah", 1)
    }
}
