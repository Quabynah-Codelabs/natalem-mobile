package io.codelabs.natalem.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "savedDate")
data class SavedDate(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var year: Int,
    var day: Int,
    var month: Int
) : Parcelable {

    @Ignore
    constructor(year: Int, month: Int, day: Int) : this(0, year, day, month)
}