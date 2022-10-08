package com.pac.kotlin_mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pac.kotlin_mobile.databinding.FragmentDetailLostBinding
import com.pac.kotlin_mobile.databinding.FragmentDetailsBinding

class FragmentDetailLost: Fragment() {
    private lateinit var binding: FragmentDetailLostBinding
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
        imageButton?.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().add(
                R.id.frameLayout,
                missing_cat()
            ).commit()
            (activity as AppCompatActivity).supportActionBar?.setCustomView(null)
        }
        return binding.root
    }

}