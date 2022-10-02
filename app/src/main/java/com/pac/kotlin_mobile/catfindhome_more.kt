package com.pac.kotlin_mobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pac.kotlin_mobile.databinding.FragmentCatfindhomeMoreBinding



class catfindhome_more : Fragment() {
    private lateinit var binding: FragmentCatfindhomeMoreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatfindhomeMoreBinding.inflate(layoutInflater)
        return binding.root
    }


}