package com.example.fastcampus.part3.design

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fastcampus.part3.design.model.Image
import com.example.fastcampus.part3.design.repository.ImageRepository
import io.reactivex.disposables.CompositeDisposable

class ImageViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    //라이브 데이터를 추가해줌
    private val _imageLiveData = MutableLiveData<Image>()
    val imageLiveData: LiveData<Image> by lazy { _imageLiveData }

    //rx를 사용하기 위해서
    private var disposable: CompositeDisposable? = CompositeDisposable()

    fun loadImage() {
        disposable?.add(imageRepository.getRandomImage()
            .doOnSuccess {

            }
            .subscribe { item ->
                _imageLiveData.value = item
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable = null
    }

    //activity에서 viewModel을 호출하기 위함
    class ImageViewModelFactory(private val imageRepository: ImageRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ImageViewModel(imageRepository) as T
        }
    }

}