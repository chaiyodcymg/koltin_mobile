package com.pac.kotlin_mobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.FragmentAddCatFindhouse2Binding
import com.pac.kotlin_mobile.databinding.FragmentAddCatFindhouseBinding
import com.pac.kotlin_mobile.databinding.FragmentAddPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Add_Cat_findhouse : Fragment() {

    private lateinit var binding: FragmentAddCatFindhouse2Binding
    var URL_API = URL.URL_API
    lateinit var AUTH : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        binding = FragmentAddCatFindhouse2Binding.inflate(layoutInflater)
        AUTH = requireActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        binding.selectVaccineDate.setOnClickListener {

            val newDateFragment = DatePickerFragment()
            val supportFragmentManager = activity?.supportFragmentManager
            if (supportFragmentManager != null) {
                newDateFragment.show(supportFragmentManager, "Date Picker")
            }
        }
//        binding.cancel.setOnClickListener {
//            var fragment_post: Fragment? = null
//            fragment_post = AddPostFragment()
//            replaceFragment(fragment_post)
//        }

        binding.submit.setOnClickListener {

            var selectID: Int = binding.gender.checkedRadioButtonId
            var radioButtonChecked: RadioButton = requireView().findViewById(selectID)
            var status: Int = 0
            var type :Int = 0
            var id =  AUTH.getString("id","")
            val api: FindhouseAPI = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FindhouseAPI::class.java)
            api.insertpost(
                binding.edtName.text.toString(),
                radioButtonChecked.text.toString(),
                binding.edtColor.text.toString(),
                binding.edtVacine.text.toString(),
                binding.date.text.toString(),
                binding.edtSpecies.text.toString(),
                binding.edtMore.text.toString(),
                binding.imageSelected.toString(),
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
                type,
                status,
                id.toString().toInt()
            ).enqueue(object : Callback<Findhouse> {
                override fun onResponse(call: Call<Findhouse>, response: Response<Findhouse>) {
                    if (response.isSuccessful) {
                        Toast.makeText( activity, "Seccessfully Inserted",
                            Toast.LENGTH_LONG).show()
                        var fragment: Fragment? = null
                        fragment = AddPostFragment()
                        replaceFragment(fragment)
                    } else {
                        Toast.makeText(activity, " Insert Failure",
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<Findhouse>, t: Throwable) {
                    Toast.makeText(activity,"Error onFailure " + t.message,
                        Toast.LENGTH_LONG).show()
                }
            })
        }



        return binding.root
    }


    fun replaceFragment(someFragment:Fragment){
        var binding: ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}

