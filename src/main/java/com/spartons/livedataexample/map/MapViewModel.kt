package com.spartons.livedataexample.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.spartons.livedataexample.model.User
import com.spartons.livedataexample.repo.UserRepo

class MapViewModel : ViewModel() {

    private val newName = MutableLiveData<String>()
    private val userRepo = UserRepo()

    val userAddedData: LiveData<List<User>>? = Transformations.map(
            newName,
            ::someFunc          // someFunc(newName)
    )

    private fun someFunc(name: String) = userRepo.addUserToList(name)

    fun addNewUser(name: String) = apply {
        newName.value = name
    }

    fun updateList(name: String) = userRepo.updateList(name)
}