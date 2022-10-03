package com.pac.kotlin_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.FragmentAddPostBinding
import com.pac.kotlin_mobile.databinding.FragmentHomeBinding



/**
 * A simple [Fragment] subclass.
 * Use the [AddPostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddPostFragment : Fragment() {
    private lateinit var binding: FragmentAddPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddPostBinding.inflate(layoutInflater)
    binding.btnFindhouse.setOnClickListener {
        var fragment: Fragment? = null
        fragment = Add_Cat_findhouse()
        replaceFragment(fragment)
    }
        return binding.root
    }

    fun replaceFragment(someFragment:Fragment){
        var binding: ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

