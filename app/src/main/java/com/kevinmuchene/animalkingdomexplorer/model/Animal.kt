package com.kevinmuchene.animalkingdomexplorer.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val habitat: String,
    val diet: String
): Parcelable
