package com.example.casonovaera

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NovaEraViewModel (application: Application): AndroidViewModel(application) {

    private val repository: NovaEraRepository
    val novaEraDataFromDB: LiveData<List<NovaEraEntity>>

    init {
       val dao = NovaEraDB.getDataBase(application).getNovaEraDao()
       repository = NovaEraRepository(dao)
       viewModelScope.launch {
           repository.getNovaEraWhitCoroutines()
       }
        novaEraDataFromDB = repository.listNovaEra
    }
    fun returnDetail(id: Int): LiveData<List<NovaEraDetailEntity>> =
        repository.getAllNovaEraDaoDB(id)

    fun getDetail(id:Int) = viewModelScope.launch {
        repository.getNovaEraDetail(id)
    }

}