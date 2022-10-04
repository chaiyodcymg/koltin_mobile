package com.pac.kotlin_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pac.kotlin_mobile.URL.URL_API
import com.pac.kotlin_mobile.databinding.ActivityMainBinding

import com.pac.kotlin_mobile.databinding.FragmentMyPostBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPostFragment : Fragment() {
    private lateinit var binding: FragmentMyPostBinding
    val postlist = arrayListOf<Post>()
    val api: Mypost_API = Retrofit.Builder()
        .baseUrl(URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Mypost_API::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyPostBinding.inflate(layoutInflater)
        binding.checkPostText.setOnClickListener {
            var fragment: Fragment? = null
            fragment = CheckPostFragment()
            replaceFragment(fragment)
        }

        val context = requireActivity().applicationContext
//        binding.recyclerView.adapter = MyPostAdapter()
        return binding.root
    }

//    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
//
//        super.onViewCreated(itemView, savedInstanceState)
//        binding.recyclerView.apply {
//            // set a LinearLayoutManager to handle Android
//            // RecyclerView behavior
//            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
//            // set the custom adapter to the RecyclerView
//            adapter = MyPostAdapter( postlist, requireActivity().applicationContext)
//        }
//    }

        fun replaceFragment(someFragment: Fragment) {
            var binding: ActivityMainBinding
            binding = ActivityMainBinding.inflate(layoutInflater)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(binding.frameLayout.id, someFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


//    fun delete(v: View){
//        val myBuilder = AlertDialog.Builder(this)
//        myBuilder.apply {
//            setTitle("Warning")
//            setMessage("ต้องการลบโพสต์ใช่ไหมคะ ?")
//            setNegativeButton("Yes") { dialog, which ->
//                api.delete(id).equals(id.toInt())
//                    .enqueue(object : Callback<Post> {
//                        override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                            if (response.isSuccessful) {
//                                Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
//                            }
//                        }
//                        override fun onFailure(call: Call<Post>, t: Throwable) {
//                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                        }
//                    })
//
//            }
//            setPositiveButton ("No"){ dialog, which ->dialog.cancel()}
//            show()
//        }
//    }
    }

