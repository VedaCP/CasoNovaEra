package com.example.casonovaera

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NovaEraDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNovaEraDao(list: List<NovaEraEntity>)

    @Query("SELECT * FROM novaEra_table")
    fun getAllNovaEraDaoDB(): LiveData<List<NovaEraEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNovaEraDetail(list: List<NovaEraDetailEntity>)

    @Query("SELECT * FROM detail_table WHERE id = :id")
    fun getNovaEraDetail(id:Int): LiveData<List<NovaEraDetailEntity>>
}