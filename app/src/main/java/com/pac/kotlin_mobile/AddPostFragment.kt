package com.pac.kotlin_mobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pac.kotlin_mobile.databinding.*



class AddPostFragment : Fragment() {
    private lateinit var binding: FragmentAddPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPostBinding.inflate(layoutInflater)
        binding.findHouse.setOnClickListener() {
            val  intent = Intent(requireActivity().applicationContext, AddCatFindhouseActivity::class.java)
           startActivity(intent)
        }
        binding.findCat.setOnClickListener {
            val  intent = Intent(requireActivity().applicationContext, LostCatActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}
