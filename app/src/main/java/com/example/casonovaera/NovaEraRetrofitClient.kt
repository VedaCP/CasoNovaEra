package com.example.casonovaera

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NovaEraRetrofitClient {
    companion object{
        private const val URL_BASE =
            "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/products/"
        fun retrofitInstance(): NovaEraAPI{
            val retrofitClient = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClient.create(NovaEraAPI::class.java)

        }
    }
}