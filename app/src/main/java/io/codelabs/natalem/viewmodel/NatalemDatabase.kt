package io.codelabs.natalem.viewmodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.codelabs.natalem.model.SavedDate

@Database(entities = [SavedDate::class], version = 1, exportSchema = false)
abstract class NatalemDatabase : RoomDatabase() {

    companion object {
        private var instance: NatalemDatabase? = null

        fun getInstance(context: Context): NatalemDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, NatalemDatabase::class.java, "natalem_db")
                    .fallbackToDestructiveMigration()
                    .build().also { instance = it }
            }
        }
    }

    abstract fun dao(): NatalemDao
}