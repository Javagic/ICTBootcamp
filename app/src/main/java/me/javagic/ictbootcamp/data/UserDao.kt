package me.javagic.ictbootcamp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import me.javagic.ictbootcamp.model.User

/**
 * Dao - Data Access Object - связующее звено между ООП объектами и реляционным представлением БД
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)  

    //Использование стандартных предопределенных аннотаций
    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    //Можно писать собственные запросы через @Query
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

}