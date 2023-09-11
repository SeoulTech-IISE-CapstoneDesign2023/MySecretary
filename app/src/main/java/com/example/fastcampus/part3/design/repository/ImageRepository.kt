package com.example.fastcampus.part3.design.repository

import com.example.fastcampus.part3.design.model.Image
import io.reactivex.Single

interface ImageRepository {

    fun getRandomImage() : Single<Image>
}