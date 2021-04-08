package me.javagic.ictbootcamp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import me.javagic.ictbootcamp.model.User

const val DATABASE_VERSION = 2
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
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                return instance
            }
        }

        /**
         * Применяем любой набор миграций при создании нашей базы данных
         * Таким образом мы уверены что схема будет обновлена без потери данных пользователя
         */
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""ALTER TABLE 'user_table' ADD COLUMN 'lastName' TEXT NOT NULL DEFAULT "LastName" """)
            }
        }
    }

}