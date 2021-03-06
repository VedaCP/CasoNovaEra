package com.example.casonovaera

import android.graphics.drawable.AnimatedImageDrawable
import android.util.Log
import androidx.lifecycle.LiveData

class NovaEraRepository(private val dao: NovaEraDao) {

    val listNovaEra: LiveData<List<NovaEraEntity>> = dao.getAllNovaEraDaoDB()

    fun converter(converter: List<NovaEraList>): List<NovaEraEntity> {
        val converterNEEntity: MutableList<NovaEraEntity> = mutableListOf()
        converter.map {
            converterNEEntity.add(NovaEraEntity(id = it.id, name = it.name, price = it.price,
                image = it.image))
        }
        return converterNEEntity
    }
    fun detailNovaEra(id: Int, name: String, price: Int, image: String, description: String,
                      lastPrice: Int, credit: Boolean): List<NovaEraDetailEntity>{
        val listNovaEraDetail: MutableList<NovaEraDetailEntity> = mutableListOf()
        listNovaEraDetail.add(
            NovaEraDetailEntity(
                id = id, name = name, price = price, image = image, description = description,
                lastPrice = lastPrice, credit = credit))
        return listNovaEraDetail
    }
    suspend fun getNovaEraWhitCoroutines(){
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = NovaEraRetrofitClient.retrofitInstance().fetchNovaEraList()
            when (response.isSuccessful){
                true -> response.body()?.let {
                    dao.insertAllNovaEraDao(it)
                }
                false -> Log.d("ERROR", "${response.code()} : ${response.errorBody()}")
            }
        }
        catch (t:Throwable){
            Log.e("ERROR COROUTINE", t.message.toString())
        }
    }
    suspend fun getNovaEraDetail(id:Int) {
        try {
            val response = NovaEraRetrofitClient.retrofitInstance().fetchNovaEraDetailEntity(id)
            when (response.isSuccessful) {
                true -> response.body()?.let {
                   dao.insertNovaEraDetail(detailNovaEra(id, it.name, it.price, it.image,
                       it.description, it.lastPrice, it.credit))
                }
                false -> Log.d("ERROR", "${response.code()} : ${response.errorBody()}")
            }
        } catch (t: Throwable) {
            Log.e("ERROR COROUTINE", t.message.toString())
        }
    }
    fun getAllNovaEraDaoDB(id: Int): LiveData<List<NovaEraDetailEntity>> =
        dao.getNovaEraDetail(id)


}