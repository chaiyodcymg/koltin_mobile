package com.pac.kotlin_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pac.kotlin_mobile.databinding.ActivitySearchDetailBinding

class SearchDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivitySearchDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}