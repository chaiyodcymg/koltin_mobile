package com.pac.kotlin_mobile

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.pac.kotlin_mobile.databinding.ActivityProfileBinding
import com.pac.kotlin_mobile.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    lateinit var AUTH : SharedPreferences
    var URL_API = URL.URL_API

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.changeToRegis.setOnClickListener{
            var binding: ActivityProfileBinding
            binding = ActivityProfileBinding.inflate(layoutInflater)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(binding.frameLayout.id, RegisterFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.btnLogin.setOnClickListener {
            val api: UserAPI = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserAPI::class.java)

            var email =  binding.email.text.toString()
            var pass = binding.password.text.toString()

            api.Login(
                email,
                pass

            ).enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: retrofit2.Response<Login>) {
                    if (response.isSuccessful()) {
                        Toast.makeText(activity?.applicationContext,"Successfully Inserted", Toast.LENGTH_SHORT).show()

                        AUTH = requireActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE)

                        AUTH.edit {
                            putString("id", response.body()?.AUTH.toString())
                            putString("role",response.body()?.role.toString())
                        }


                        var binding: ActivityProfileBinding
                        binding = ActivityProfileBinding.inflate(layoutInflater)
                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.replace(binding.frameLayout.id, ProfileFragment())
                        transaction.addToBackStack(null)
                        transaction.commit()
                    } else {

                        val myBuilder = AlertDialog.Builder(activity?.applicationContext)
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

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Toast.makeText(activity?.applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
        return binding.root
    }

}