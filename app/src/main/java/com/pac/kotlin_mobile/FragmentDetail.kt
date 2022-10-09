package com.pac.kotlin_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity


import com.pac.kotlin_mobile.databinding.FragmentDetailsBinding
import com.pac.kotlin_mobile.databinding.FragmentNewsInfoBinding


class FragmentDetail : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private var id: String? = ""
    private var name: String? = ""
    private var gender: String? = ""
    private var color: String? = ""
    private var species: String? = ""
    private var vaccine: String? = ""
    private var date_vaccine: String? = ""
    private var more_info: String? = ""
    private var address: String? = ""
    private var firstname: String? = ""
    private var lastname: String? = ""
    private var phone: String? = ""
    private var email: String? = ""
    private var line_id: String? = ""
    private var facebook: String? = ""
    private var image: String? = ""
    private var house_no: String? = ""
    private var street: String? = ""
    private var sub_district : String? = ""
    private var district: String? = ""
    private var province: String? = ""
    private var post_address : String? = ""
    private var name_owner : String? = ""
//    private var home : String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        // แก้ไขปุ่มย้อนกลับ
        (activity as AppCompatActivity).supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowCustomEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        (activity as AppCompatActivity).supportActionBar?.elevation = 0.0F
        val view = (activity as AppCompatActivity).supportActionBar?.customView

        val imageButton = view?.findViewById<View>(R.id.action_bar_back)
//        home = arguments?.getString("Home")
        if (arguments?.getString("Home").isNullOrBlank()){
            imageButton?.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.frameLayout,
                        catfindhome_more()
                    ).commit()
                (activity as AppCompatActivity).supportActionBar?.setCustomView(null)
            }
        }else{
            imageButton?.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.frameLayout,
                        HomeFragment()
                    ).commit()
                (activity as AppCompatActivity).supportActionBar?.setCustomView(null)
            }
        }


        id = arguments?.getString("id")
        name = arguments?.getString("name")
        gender = arguments?.getString("gender")
        color = arguments?.getString("color")
        species = arguments?.getString("species")
        vaccine = arguments?.getString("vaccine")
        date_vaccine = arguments?.getString("date_vaccine")
        more_info = arguments?.getString("more_info")
        image = arguments?.getString("image")
        address = arguments?.getString("address")
        house_no = arguments?.getString("house_no")
        street = arguments?.getString("street")
        sub_district = arguments?.getString("sub_district")
        district = arguments?.getString("district")
        province = arguments?.getString("province")
        post_address = arguments?.getString("post_address")
        firstname = arguments?.getString("firstname")
        lastname = arguments?.getString("lastname")
        phone = arguments?.getString("phone")
        email = arguments?.getString("email")
        line_id = arguments?.getString("line_id")
        facebook = arguments?.getString("facebook")
        name_owner = arguments?.getString("name_owner")


        val outputId = binding.id
        outputId.text = "รหัสแมว : "+id.toString()
        val outputName = binding.name
        outputName.text = "ชื่อ : "+name.toString()
        val outputgender = binding.gender
        outputgender.text = "เพศ : "+gender.toString()
        val outputcolor = binding.color
        outputcolor.text = "สี : "+color.toString()
        val outputspecies = binding.color
        outputspecies.text = "สายพันธ์ุ : "+species.toString()
        val outputvaccine = binding.vaccine
        outputvaccine.text = "วัคซีน : "+vaccine.toString()
        val outputdate_vaccine = binding.dateVaccine
        outputdate_vaccine.text = "วันที่ได้รับวัคซีน : "+date_vaccine.toString()
        val outputmore_info = binding.detail
        outputmore_info.text =more_info.toString()
        val outputaddress = binding.address
        outputaddress.text="บ้านเลขที่ : "+ house_no.toString()+"\nถนน : "+ street.toString()+
                "\nตำบล : "+ sub_district.toString()+"\nอำเภอ : "+ district.toString()+"\nจังหวัด : "+ province.toString()+"\nรหัสไปรยษณีย์ : "+ post_address.toString()
        val outputname_owner = binding.nameOwner
        outputname_owner.text ="ชื่อ : "+ firstname.toString()+" "+firstname.toString()
        val outputphone = binding.phone
        outputphone.text ="เบอร์โทร : "+ phone.toString()
        val outputemail = binding.email
        outputemail.text ="อีเมล : "+ email.toString()
        val outputline_id = binding.line
        outputline_id.text ="Line ID : "+ line_id.toString()
        val outputfacebook = binding.facebook
        outputfacebook.text ="Facebook : "+ facebook.toString()
        return binding.root
    }


}