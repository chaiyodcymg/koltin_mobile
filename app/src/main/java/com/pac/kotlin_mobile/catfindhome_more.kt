package com.pac.kotlin_mobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.FragmentCatfindhomeMoreBinding
import com.pac.kotlin_mobile.databinding.FragmentHomeBinding


class catfindhome_more : Fragment() {
    private lateinit var binding: FragmentCatfindhomeMoreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatfindhomeMoreBinding.inflate(layoutInflater)

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
                HomeFragment()
            ).commit()
            (activity as AppCompatActivity).supportActionBar?.setCustomView(null)
        }

        binding.catFind.setOnClickListener {
            var detailfragment: Fragment = FragmentDetail()

            replaceFragment(detailfragment)
        }
        binding.btnHome.setOnClickListener {
            var detailfragment: Fragment =  FragmentDetail()

            replaceFragment(detailfragment)
        }
        return binding.root
    }
    fun replaceFragment(someFragment: Fragment) {
        var binding: ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}