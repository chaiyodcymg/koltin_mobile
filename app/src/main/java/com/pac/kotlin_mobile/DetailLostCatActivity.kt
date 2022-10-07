package com.pac.kotlin_mobile

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pac.kotlin_mobile.databinding.ActivityDetailLostCatBinding
import com.pac.kotlin_mobile.databinding.ActivitySearchBinding

class DetailLostCatActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailLostCatBinding
    lateinit var AUTH : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLostCatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}