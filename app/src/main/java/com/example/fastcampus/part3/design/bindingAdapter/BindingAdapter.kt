package com.example.fastcampus.part3.design.bindingAdapter

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.fastcampus.part3.design.R
import com.example.fastcampus.part3.design.model.Image
import com.example.fastcampus.part3.design.model.Todo
import com.example.fastcampus.part3.design.model.Type
import java.text.SimpleDateFormat
import java.util.Date

@BindingAdapter("image")
fun ImageView.setImage(image: Image?) {

    if (image == null) {
        return
    }
    setBackgroundColor(Color.parseColor(image.color))
    load(image.url) {
        crossfade(300)
    }
}

@BindingAdapter("type")
fun ImageView.setType(type: Type?) {
    if (type == null) {
        return
    }

    when (type) {
        Type.COMPLETE -> {
            setBackgroundResource(R.drawable.baseline_mood_green_24)
        }

        Type.READY -> {
            setBackgroundResource(R.drawable.baseline_mood_24)
        }
    }
}