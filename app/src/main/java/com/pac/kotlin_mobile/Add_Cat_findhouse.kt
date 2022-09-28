package com.pac.kotlin_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.pac.kotlin_mobile.databinding.FragmentAddCatFindhouseBinding
import com.pac.kotlin_mobile.databinding.FragmentAddPostBinding


class Add_Cat_findhouse : Fragment() {

    private lateinit var binding: FragmentAddCatFindhouseBinding
    var gender :String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentAddCatFindhouseBinding.inflate(layoutInflater)
        binding.edtDate.setOnClickListener {

        }
        return binding.root
    }

}