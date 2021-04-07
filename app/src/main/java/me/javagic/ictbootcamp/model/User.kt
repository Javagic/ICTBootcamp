package me.javagic.ictbootcamp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: Int,
    val firstName: String,
    val number: Int
): Parcelable