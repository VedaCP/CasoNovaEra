package com.example.casonovaera

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NovaEraEntity::class, NovaEraDetailEntity::class], version = 1)

abstract class NovaEraDB: RoomDatabase(){
    abstract fun getNovaEraDao(): NovaEraDao

    companion object{
        @Volatile
        private var INSTANCE: NovaEraDB? = null
        fun getDataBase(context: Context): NovaEraDB{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                NovaEraDB::class.java, "products").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
