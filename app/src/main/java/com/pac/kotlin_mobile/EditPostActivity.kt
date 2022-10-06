package com.pac.kotlin_mobile

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.pac.kotlin_mobile.databinding.ActivityEditPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditPostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var Select_Page : Int = R.id.page_1
        val actionBar : ActionBar? = supportActionBar
        binding = ActivityEditPostBinding.inflate(layoutInflater)
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
        binding.selectVaccineDate.setOnClickListener {

            val newDateFragment = DatePickerFragment()
            val supportFragmentManager = supportFragmentManager
            if (supportFragmentManager != null) {
                newDateFragment.show(supportFragmentManager, "Date Picker")
            }
        }

        val id = intent.getStringExtra("id").toString()
        val namecat = intent.getStringExtra("namecat")
        val gender = intent.getStringExtra("gender")
        val color = intent.getStringExtra("color")
        val vaccine = intent.getStringExtra("vaccine")
        val date = intent.getStringExtra("date")
        val species = intent.getStringExtra("species")
        val more_info = intent.getStringExtra("more_info")
//        val image = intent.getStringExtra("image")
        val house_no = intent.getStringExtra("house_no")
        val street = intent.getStringExtra("street")
        val sub_district = intent.getStringExtra("sub_district")
        val district = intent.getStringExtra("district")
        val province = intent.getStringExtra("province")
        val post_address = intent.getStringExtra("post_address")
        val firstname = intent.getStringExtra("firstname")
        val lastname = intent.getStringExtra("lastname")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val line_id = intent.getStringExtra("line_id")
        val facebook = intent.getStringExtra("facebook")


        binding.edtName.setText(namecat)
        if(gender == "ผู้") {
            binding.male.setChecked(true)
        }else{
            binding.famale.setChecked(true)
        }
        binding.edtColor.setText(color)
        binding.edtVacine.setText(vaccine)
        binding.date.setText(date)
        binding.edtSpecies.setText(species)
        binding.edtMore.setText(more_info)
//        binding.imageSelected.setText(image)
        binding.edtPlace.setText(house_no)
        binding.edtStreet.setText(street)
        binding.edtTown.setText(sub_district)
        binding.edtDistrict.setText(district)
        binding.edtProvince.setText(province)
        binding.edtPostcode.setText(post_address)
        binding.edtNameperson.setText(firstname)
        binding.edtLastnameperson.setText(lastname)
        binding.edtPhone.setText(phone)
        binding.edtEmail.setText(email)
        binding.edtLineid.setText(line_id)
        binding.edtFacebook.setText(facebook)


        binding.submitUpdate.setOnClickListener(){
            var selectID: Int = binding.gender.checkedRadioButtonId
            var radioButtonChecked: RadioButton = findViewById(selectID)
            var URL_API = URL.URL_API
            val api: Cat_API = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Cat_API::class.java)
            api.updatePost(
                id.toInt(),
                binding.edtName.text.toString(),
                radioButtonChecked.text.toString(),
                binding.edtColor.text.toString(),
                binding.edtVacine.text.toString(),
                binding.date.text.toString(),
                binding.edtSpecies.text.toString(),
                binding.edtMore.text.toString(),
//                binding.imageSelected.setImageURI(image),
                binding.edtPlace.text.toString(),
                binding.edtStreet.text.toString(),
                binding.edtTown.text.toString(),
                binding.edtDistrict.text.toString(),
                binding.edtProvince.text.toString(),
                binding.edtPostcode.text.toString(),
                binding.edtNameperson.text.toString(),
                binding.edtLastnameperson.text.toString(),
                binding.edtPhone.text.toString(),
                binding.edtEmail.text.toString(),
                binding.edtLineid.text.toString(),
                binding.edtFacebook.text.toString(),
            )
                .enqueue(object : Callback<Cat> {
                    override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                        if(response.isSuccessful) {
                            Toast.makeText(applicationContext, "Successfully Update", Toast.LENGTH_LONG).show()
                            finish()
                        }else{
                            Toast.makeText(applicationContext,"Error ", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<Cat>, t: Throwable) {
                        Toast.makeText(applicationContext.applicationContext, "Update Fail", Toast.LENGTH_LONG).show()
                    }

                })
        }
    }


}