package me.javagic.ictbootcamp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import me.javagic.ictbootcamp.data.DataKeeper
import me.javagic.ictbootcamp.model.User

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val dataKeeper: DataKeeper = DataKeeper

    init {
        readAllData = dataKeeper.readAllData()
    }

    fun addUser(user: User) {
        dataKeeper.addUser(user)
    }

    fun updateUser(user: User) {
        dataKeeper.updateUser(user)
    }

    fun deleteUser(user: User) {
        dataKeeper.deleteUser(user)
    }

    fun deleteAllUsers() {
        dataKeeper.deleteAllUsers()
    }

}