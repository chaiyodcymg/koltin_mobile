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

import com.pac.kotlin_mobile.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    lateinit var AUTH : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun register(v:View){
        val api: UserAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)

        var email = binding.email.text.toString()
        var pass = binding.password.text.toString()
        var fname = binding.firstname.text.toString()
        var lname = binding.lastname.text.toString()
        api.Register(

            email,
            pass,
            fname,
            lname
        ).enqueue(object : Callback<Register> {
            override fun onResponse(
                call: Call<Register>,
                response: retrofit2.Response<Register>
            ) {
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

                    val myBuilder = AlertDialog.Builder(this@RegisterActivity)
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

            override fun onFailure(call: Call<Register>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun change_to_login(v: View){
        val i = Intent(applicationContext, LoginActivity::class.java)
        startActivity(i)
    }
}