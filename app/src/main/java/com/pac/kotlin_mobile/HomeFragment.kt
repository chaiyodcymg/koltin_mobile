package com.pac.kotlin_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.pac.kotlin_mobile.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    var data_List = arrayListOf<Profile>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.seemoreCat.setOnClickListener() {
            var seemorefragment: Fragment? = null
            seemorefragment = catfindhome_more()
            replaceFragment(seemorefragment)
        }
        binding.postShort.setOnClickListener() {
            var detailfragment: Fragment? = null
            detailfragment = catfindhome_more()
            replaceFragment(detailfragment)

        }

        binding.missingCat.setOnClickListener() {
            var missingCatfragment: Fragment? = null
            missingCatfragment = missing_cat()
            replaceFragment(missingCatfragment)
        }
        binding.newsRecyclerview.adapter = HomeNewsAdapter(this.data_List,requireActivity().applicationContext)
        binding.newsRecyclerview.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
       data_List.add(Profile("email","fn","l","/images/U1M9hDysU1YwzZ2kzCtoQRRxnUq1DiN5HFQiO4PxLYx66bO43V.jpg"))
        binding.newsRecyclerview.adapter = HomeNewsAdapter(data_List,requireActivity().applicationContext)
        return binding.root
    }

    fun replaceFragment(someFragment:Fragment){
        var binding: FragmentHomeBinding
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}