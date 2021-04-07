package me.javagic.ictbootcamp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.javagic.ictbootcamp.model.User

object DataKeeper {
    private val userStore: MutableCollection<User> = mutableSetOf()
    private val liveData: MutableLiveData<List<User>> = MutableLiveData()

    fun addUser(user: User) {
        user.id = userStore.size
        userStore.add(user)
        liveData.postValue(userStore.toList())
    }

    fun updateUser(user: User) {
        userStore.removeAll { it.id == user.id }
        userStore.add(user)
        liveData.postValue(userStore.toList())
    }

    fun deleteUser(user: User) {
        userStore.remove(user)
        liveData.postValue(userStore.toList())
    }

    fun deleteAllUsers() {
        userStore.clear()
        liveData.postValue(userStore.toList())
    }

    fun readAllData(): LiveData<List<User>> = liveData
}