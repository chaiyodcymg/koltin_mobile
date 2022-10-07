package com.pac.kotlin_mobile

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pac.kotlin_mobile.databinding.ActivityDetailFindhouseBinding
import com.pac.kotlin_mobile.databinding.ActivityDetailLostCatBinding
import com.pac.kotlin_mobile.databinding.ActivitySearchBinding

class DetailFindhouseActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailFindhouseBinding
    lateinit var AUTH : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFindhouseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var data = intent.extras
        var search:SearchPacelable? = data?.getParcelable("search")

    }
}