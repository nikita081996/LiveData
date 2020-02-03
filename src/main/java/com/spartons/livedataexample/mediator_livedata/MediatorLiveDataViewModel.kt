package com.spartons.livedataexample.mediator_livedata

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MediatorLiveDataViewModel : ViewModel() {

    private val firstNameLiveData = MutableLiveData<String>()

    private val lastNameLiveData = MutableLiveData<String>()

    val fullName = MediatorLiveData<String>()

    init {
        fullName.addSource(firstNameLiveData) { result: String? ->
            result?.let { fullName.value = concateFullName(it, true) }
        }

        fullName.addSource(lastNameLiveData) { result: String? ->
            result?.let { fullName.value = concateFullName(it, false) }
        }
    }

    fun concateFullName(name: String, bool: Boolean): String {
        if (bool) {
            return name + if (lastNameLiveData.value != null) {
                lastNameLiveData.value
            } else {
                ""
            }
        } else
            return (if (firstNameLiveData.value != null) {
                firstNameLiveData.value
            } else {
                ""
            } + name)
    }


    fun addName(user: String, bool: Boolean) = apply {
        if (bool)
            firstNameLiveData.value = user
        else
            lastNameLiveData.value = user
    }
}