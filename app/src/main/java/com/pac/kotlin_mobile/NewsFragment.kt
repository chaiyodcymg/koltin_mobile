package com.pac.kotlin_mobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pac.kotlin_mobile.databinding.FragmentHomeBinding
import com.pac.kotlin_mobile.databinding.FragmentMyPostBinding
import com.pac.kotlin_mobile.databinding.FragmentNewsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    lateinit var AUTH : SharedPreferences
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsBinding.inflate(layoutInflater)
        binding.fab.setOnClickListener {
            var addnews: Fragment? = null
            addnews = AddNewsFragment()
            replaceFragment(addnews)
        }

        return binding.root
    }

    fun replaceFragment(someFragment:Fragment){
        var binding: FragmentNewsBinding
        binding = FragmentNewsBinding.inflate(layoutInflater)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}