package com.pac.kotlin_mobile

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.pac.kotlin_mobile.databinding.ActivityProfileBinding
import com.pac.kotlin_mobile.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    lateinit var binding : ActivitySearchBinding
    lateinit var AUTH : SharedPreferences
    var Select_Page : Int = R.id.page_1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // แก้ไขปุ่มย้อนกลับ
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar_layout)
        val view = supportActionBar!!.customView
        val imageButton = view.findViewById<View>(R.id.action_bar_back)
        imageButton.setOnClickListener {
            val intent = intent.putExtra("Select_Page",Select_Page)
            setResult( Activity.RESULT_OK,intent)
            finish()
        }
        Select_Page = intent.getIntExtra("Select_Page",0)
    }
}