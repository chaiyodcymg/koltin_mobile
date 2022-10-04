package com.pac.kotlin_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pac.kotlin_mobile.databinding.FragmentAddCatFindhouse2Binding
import com.pac.kotlin_mobile.databinding.FragmentAddPostBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_add_cat_findhouse.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCatFindhouse : Fragment() {
    private lateinit var binding: FragmentAddCatFindhouse2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddCatFindhouse2Binding.inflate(layoutInflater)
        binding.btnImageUpload.setOnClickListener {
            Toast.makeText ( activity?.applicationContext,"Hello", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

}