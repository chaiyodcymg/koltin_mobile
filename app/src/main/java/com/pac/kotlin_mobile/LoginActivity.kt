package com.pac.kotlin_mobile

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import com.pac.kotlin_mobile.databinding.ActivityLoginBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var AUTH : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
    fun login(v: View){

        val api: UserAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)

        var email =  binding.email.text.toString()
        var pass = binding.password.text.toString()
        api.Login(
            email,
            pass

        ).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>,response: retrofit2.Response<User>) {
                if (response.isSuccessful()) {
                    Toast.makeText(applicationContext,"Successfully Inserted", Toast.LENGTH_SHORT).show()
                    Log.i("Event","${response.body()?.AUTH}")
                    AUTH  = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
                    AUTH.edit {
                        putString("id", response.body()?.AUTH.toString())
                    }
                    val i = Intent(applicationContext, HomeActivity::class.java)

                    startActivity(i)
                } else {

                    val myBuilder = AlertDialog.Builder(this@LoginActivity)
                    myBuilder.apply {
                        setTitle("ข้อผิดพลาดในการเข้าสู่ระบบ")
                        setMessage("อีเมลหรือรหัสผ่านไม่ถูกต้องกรุณากรอกข้อมูลใหม่อีกรั้ง")
                        setPositiveButton("ตกลง") { dialog, which ->
//                            Toast.makeText(applicationContext, "Click on Yes", Toast.LENGTH_SHORT)
//                                .show()
                        }

                        show()
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onResume() {
        super.onResume()
        Log.i("Event", "Login ")

    }
    override fun onBackPressed() {
        finishAffinity()
    }
    fun change_to_register(v:View){
        val i = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(i)
    }

}