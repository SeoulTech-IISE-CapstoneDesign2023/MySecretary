package com.example.fastcampus.part3.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.fastcampus.part3.design.databinding.ActivityMainBinding
import com.example.fastcampus.part3.design.pagerAdapter.PagerAdapter
import com.example.fastcampus.part3.design.repository.ImageRepositoryImpl
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ImageViewModel by viewModels {
        ImageViewModel.ImageViewModelFactory(ImageRepositoryImpl())
    }

    private val tabArray = arrayOf("시간", "공간")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = pagerAdapter
        with(binding) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                if (position == 0) {
                    tab.icon = getDrawable(R.drawable.baseline_calendar_month_24)
                } else {
                    tab.icon = getDrawable(R.drawable.baseline_map_24)
                }
                tab.text = tabArray[position]
            }.attach()
        }
        loadImage()
        initFloatingButton()
    }

    private fun loadImage() {
        viewModel.loadImage()
    }

    private fun initFloatingButton(){
        binding.scrollView.setOnScrollChangeListener{_, _, scrollY, _, _ ->
            if (scrollY == 0) {
                binding.floatingActionButton.extend()
            } else {
                binding.floatingActionButton.shrink()
            }
        }
    }

}