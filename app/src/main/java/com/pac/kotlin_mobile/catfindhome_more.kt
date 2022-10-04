package com.pac.kotlin_mobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.catFind.setOnClickListener() {
            var detailfragment: Fragment? = null
            detailfragment = activity_details()
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