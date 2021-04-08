package me.javagic.ictbootcamp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 *  Любая модификация полей в [Entity] приводит к смене схемы базы данных
 *  Если БД загружается с новой схемой без миграции данных приложение либо падает
 *  Либо уничтожает базу данных с последующим её пересозданием если включен флаг [androidx.room.RoomDatabase.Builder.fallbackToDestructiveMigration]
 */
@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val firstName: String,
    val lastName: String,
    val number: Int
) : Parcelable