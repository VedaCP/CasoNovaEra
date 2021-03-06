package com.example.casonovaera

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NovaEraAPI {

    @GET("products")
    suspend fun fetchNovaEraList(): Response<List<NovaEraEntity>>

    @GET("detail/{id}")
    suspend fun fetchNovaEraDetailEntity(@Path("id") id: Int)
            : Response<NovaEraDetail>
}