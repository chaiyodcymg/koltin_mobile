package com.pac.kotlin_mobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pac.kotlin_mobile.databinding.FragmentMyPostBinding
import com.pac.kotlin_mobile.databinding.FragmentNotLoggedInBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotLoggedInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotLoggedInFragment : Fragment() {
    private lateinit var binding: FragmentNotLoggedInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotLoggedInBinding.inflate(layoutInflater)
    binding.btnGotoLogin.setOnClickListener {
        val  intent = Intent(activity?.applicationContext, ProfileActivity::class.java)
        intent.putExtra("page","2")
        startActivity(intent )
    }
        return binding.root
    }
}