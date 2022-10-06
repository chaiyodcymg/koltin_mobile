package com.pac.kotlin_mobile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pac.kotlin_mobile.databinding.FragmentNewsBinding
import com.pac.kotlin_mobile.databinding.FragmentNewsInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [news_info.newInstance] factory method to
 * create an instance of this fragment.
 */
class news_info : Fragment() {
    private lateinit var binding: FragmentNewsInfoBinding
    lateinit var AUTH : SharedPreferences
    private var title: String? = ""
    private var image: String? = ""
    private var detail: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentNewsInfoBinding.inflate(layoutInflater)
        title = arguments?.getString("title")
        image = arguments?.getString("image")
        detail = arguments?.getString("detail")

        val outputTitle = binding.newsTitle
        outputTitle.text = title.toString()
        val outputDetail = binding.newsInfo
        outputDetail.text = detail.toString()

        return binding.root
    }


}