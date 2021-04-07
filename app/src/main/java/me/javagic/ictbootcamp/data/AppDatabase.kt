package me.javagic.ictbootcamp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.javagic.ictbootcamp.model.User

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "app_database"

/**
 * Имплементация базы данных
 * Наследуется от [RoomDatabase] и генерирует под собой наследника [AppDatabase_Impl]
 */
@Database(entities = [User::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    /**
     * Создание потокобезопасного синглтона (не самым изящным способом) //ДЗ
     */
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}