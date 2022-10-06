package com.pac.kotlin_mobile



import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.pac.kotlin_mobile.databinding.ActivityProfileBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class ProfileActivity : AppCompatActivity() {
    lateinit var binding : ActivityProfileBinding
    lateinit var AUTH : SharedPreferences
    var Select_Page : Int = R.id.page_1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // แก้ไขปุ่มย้อนกลับ
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar_layout)
        supportActionBar!!.elevation = 0.0F
        val view = supportActionBar!!.customView
        val imageButton = view.findViewById<View>(R.id.action_bar_back)
        imageButton.setOnClickListener {
            val intent = intent.putExtra("Select_Page",Select_Page)
            setResult( Activity.RESULT_OK,intent)
            finish()
        }


        Select_Page = intent.getIntExtra("Select_Page",0)



        AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
//        AUTH.edit { clear() }
        var id =  AUTH.getString("id","")
        if(id?.isEmpty() == true){
            Log.i("Event","inn")
            supportFragmentManager.beginTransaction().add(
                R.id.frameLayout,
                LoginFragment()
            ).commit()
        }else{
            supportFragmentManager.beginTransaction().add(
                R.id.frameLayout,
                ProfileFragment()
            ).commit()
        }

    }
    override fun onBackPressed() {

       val intent = intent.putExtra("Select_Page",Select_Page)
        setResult( Activity.RESULT_OK,intent)
        finish()
    }


}



