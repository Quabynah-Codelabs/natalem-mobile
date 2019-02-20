package io.codelabs.natalem.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.codelabs.natalem.model.SavedDate

@Dao
interface NatalemDao {

    @Insert
    fun saveDate(vararg date: SavedDate)

    @Query("SELECT * FROM savedDate ORDER BY id DESC")
    fun getDates(): LiveData<MutableList<SavedDate>>

    @Delete
    fun deleteDate(vararg date: SavedDate)

    @Query("SELECT * FROM savedDate WHERE id = :id")
    fun getSingleDate(id: Int) : LiveData<SavedDate>
}