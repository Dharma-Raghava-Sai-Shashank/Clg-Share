package com.example.clgshare

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [MyPostData::class, MyProfileData::class, MyFavouriteData::class, MyConnectionData::class,MyQueryData::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDataDao(): AppDataDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java as Class<AppDatabase>,
                    "APP DATA"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}