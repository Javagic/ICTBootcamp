package me.javagic.ictbootcamp.repository

import androidx.lifecycle.LiveData
import me.javagic.ictbootcamp.data.UserDao
import me.javagic.ictbootcamp.model.User

/**
 * Класс отвечающий за предоставление базы данных
 * Дополнительный слой абстрации доступа к данным
 * Добавляет многопоточный вариант использования через набор прерываемых функций - корутин
 */
/**
 * Любой доступ к базе данных должен совершаться из бекграунд потока!
 */
class UserRepository(private val userDao: UserDao) {

    //(использование LiveData в репозиториях не рекомендуется) //ДЗ
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

}