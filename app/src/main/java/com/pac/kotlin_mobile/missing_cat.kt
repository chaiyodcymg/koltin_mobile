package com.pac.kotlin_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.FragmentMissingCatBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [missing_cat.newInstance] factory method to
 * create an instance of this fragment.
 */
class missing_cat : Fragment() {
    private lateinit var binding: FragmentMissingCatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissingCatBinding.inflate(layoutInflater)

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

        binding.catHomelessImg.setOnClickListener {
            var detailLostfragment: Fragment = FragmentDetailLost()

            replaceFragment(detailLostfragment)
        }
        binding.catHomelessBlock.setOnClickListener {
            var detailLostfragment: Fragment =  FragmentDetailLost()

            replaceFragment(detailLostfragment)
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