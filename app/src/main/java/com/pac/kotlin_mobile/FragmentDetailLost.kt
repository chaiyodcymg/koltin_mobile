package com.pac.kotlin_mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.FragmentDetailLostBinding
import com.pac.kotlin_mobile.databinding.FragmentDetailsBinding

class FragmentDetailLost: Fragment() {
    private lateinit var binding: FragmentDetailLostBinding
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
    private var date : String? = ""
    private var notice_point : String? = ""
    private var place_to_found : String? = ""
    var URL_API = URL.URL_API
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailLostBinding.inflate(layoutInflater)
        // แก้ไขปุ่มย้อนกลับ
        (activity as AppCompatActivity).supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowCustomEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        (activity as AppCompatActivity).supportActionBar?.elevation = 0.0F
        val view = (activity as AppCompatActivity).supportActionBar?.customView

        val imageButton = view?.findViewById<View>(R.id.action_bar_back)
        if(arguments?.getString("Home").isNullOrBlank()){
            imageButton?.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout,
                    missing_cat()
                ).commit()
                (activity as AppCompatActivity).supportActionBar?.setCustomView(null)
            }
        }else{
            imageButton?.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(
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
        date = arguments?.getString("date")
        notice_point = arguments?.getString("notice_point")
        place_to_found = arguments?.getString("place_to_found")
        Glide.with(requireActivity().applicationContext).load(URL_API +image).into( binding.imageSelected)
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
        val outputcantact = binding.contact
        outputcantact.text="ชื่อ : "+ firstname.toString()+"\nนามสกุล : "+lastname.toString()+
                "\nเบอร์โทร : "+ phone.toString()+"\nอีเมล : "+ email.toString()+"\nLine ID : "+ line_id.toString()+"\nFacebook : "+ facebook.toString()

        return binding.root
    }

}