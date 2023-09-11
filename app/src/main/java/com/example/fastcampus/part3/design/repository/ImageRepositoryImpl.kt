package com.example.fastcampus.part3.design.repository

import com.example.fastcampus.part3.design.RetrofitManager
import com.example.fastcampus.part3.design.model.Image
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/*
작동원리 getRandomImage함수가 호출이 되면 retrofit을 통해 랜덤이미지 데이터를 얻어옴
subscribeOn을 통해서 io쓰레드를 통해서 실행하도록 스케줄링함 이렇게해야 ui쓰레드가 차단안되고 네트워크처리가능
observeOn을 통해서 이후의 작업을 ui쓰레드에서 수행하도록함 이렇게되면 ui관련작업은 main쓰레드에서함
flatMap을 통해 데이터를 가공시킴 새로운 Image객체생성
 */
class ImageRepositoryImpl : ImageRepository {
    override fun getRandomImage() = RetrofitManager.imageService.getRandomImageRx()
        //어느 쓰레드로 통신을 할것인지
        .subscribeOn(Schedulers.io())
        //서브 스크라이브를 처리하는 부분
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap { item ->
            Single.just(Image(item.urls.regular, item.color))
        }
}