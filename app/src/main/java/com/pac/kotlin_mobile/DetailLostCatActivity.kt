package com.pac.kotlin_mobile

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityDetailLostCatBinding
import com.pac.kotlin_mobile.databinding.ActivitySearchBinding

class DetailLostCatActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailLostCatBinding
    lateinit var AUTH : SharedPreferences
    var URL_API = URL.URL_API
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLostCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // แก้ไขปุ่มย้อนกลับ
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar_layout)
        supportActionBar!!.elevation = 0.0F
        val view = supportActionBar!!.customView

        val imageButton = view.findViewById<View>(R.id.action_bar_back)
        imageButton.setOnClickListener {
            finish()
        }


        var data = intent.extras
        var search:SearchPacelable? = data?.getParcelable("search")
        binding.id.text = "รหัสแมว : ${search?.id}"
        binding.type.text= "เรื่องที่แจ้ง : " +if (search?.type == 1) "น้องเหมียวหาย" else "พบน้องเหมียว"
        binding.name.text = "ชื่อ : ${search?.name}"
        binding.color.text ="สี : ${search?.color}"
        binding.species.text="สายพันธุ์ : ${search?.species}"
        binding.vaccine.text="วัคซีนที่เคยได้รับ : ${search?.vaccine}"
        binding.gender.text ="เพศ : ${search?.gender}"
        binding.dateVaccine.text="วันที่ได้รับวัคซีน : ${search?.date_vaccine}"
        binding.detail.text="${search?.more_info}"
        binding.date.text="วันที่พบ :${search?.date}"
        binding.noticePoint.text="จุดสังเกต : ${search?.notice_point}"
        binding.placeToFound.text="จุดที่หาย/พบ(สถานที่) : ${search?.place_to_found}"
        binding.address.text="บ้านเลขที่ : ${search?.house_no} \nถนน : ${search?.street} \nตำบล : ${search?.sub_district} \nเขต/อำเภอ : ${search?.district} \nจังหวัด : ${search?.province} \nรหัสไปรษณีย์ : ${search?.post_address}"
        binding.nameOwner.text="ชื่อ : ${search?.firstname} ${search?.lastname}"
        binding.phone.text="เบอร์โทร : ${search?.phone}"
        binding.email.text="อีเมล : ${search?.email}"
        binding.line.text="Line ID : ${search?.line_id}"
        binding.facebook.text="Facebook : ${search?.facebook}"
        Glide.with(applicationContext).load(URL_API +search?.image).into(binding.imageSelected)
    }
}