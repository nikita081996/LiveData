package com.spartons.livedataexample.swichmap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.spartons.livedataexample.model.User
import com.spartons.livedataexample.repo.UserRepo

class SwitchMapViewModel : ViewModel() {

    private val newName = MutableLiveData<String>()
    private val userRepo = UserRepo()

    val userNameResult: LiveData<List<User>> = Transformations.switchMap(
            newName,
            ::someFunc     // someFunc(newName)
    )

    private fun someFunc(name: String) = userRepo.addNewUserToList(name)

    fun addNewUser(name: String) = apply { newName.value = name }

    fun updateList(name: String) = userRepo.updateList(name)


}