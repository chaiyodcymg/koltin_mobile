package com.pac.kotlin_mobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.FragmentAddCatFindhouse2Binding
import com.pac.kotlin_mobile.databinding.FragmentLostCatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LostCatFragment : Fragment() {

    private lateinit var binding: FragmentLostCatBinding
    var URL_API = URL.URL_API
    lateinit var AUTH : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        binding = FragmentLostCatBinding.inflate(layoutInflater)
        AUTH = requireActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        binding.selectVaccineDate.setOnClickListener {

            val newDateFragment = DatePickerFragment()
            val supportFragmentManager = activity?.supportFragmentManager
            if (supportFragmentManager != null) {
                newDateFragment.show(supportFragmentManager, "Date Picker")
            }
        }
        binding.selectDate.setOnClickListener {

            val newDateFragment = DatePickerPlace()
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
            var selectID2: Int = binding.radiogroup.checkedRadioButtonId
            var radioButtonChecked2: RadioButton = requireView().findViewById(selectID2)
            var case: Int = 0;
            if(radioButtonChecked2.text.toString() == "แจ้งน้องแมวหาย"){
                case = 1
            }else{
                case = 2
            }
            var status: Int = 0
            var id =  AUTH.getString("id","")
            val api: LostcatAPI = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LostcatAPI::class.java)
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
                binding.dateplace.text.toString(),
                binding.edtSpot.text.toString(),
                binding.edtLostplace.text.toString(),
                binding.edtNameperson.text.toString(),
                binding.edtLastnameperson.text.toString(),
                binding.edtPhone.text.toString(),
                binding.edtEmail.text.toString(),
                binding.edtLineid.text.toString(),
                binding.edtFacebook.text.toString(),
                case,
                status,
                id.toString().toInt()
            ).enqueue(object : Callback<Lostcat> {
                override fun onResponse(call: Call<Lostcat>, response: Response<Lostcat>) {
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

                override fun onFailure(call: Call<Lostcat>, t: Throwable) {
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