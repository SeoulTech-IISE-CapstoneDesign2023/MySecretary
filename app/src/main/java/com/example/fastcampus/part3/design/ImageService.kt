package com.example.fastcampus.part3.design

import com.example.fastcampus.part3.design.model.ImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Authorization: Client-ID AhrX3ZTCa1Rs9BjPsqLTxN8moMLHyK7OzjL74YnlfUI")
    @GET("photos/random")
    fun getRandomImageRx() : Single<ImageResponse>
}