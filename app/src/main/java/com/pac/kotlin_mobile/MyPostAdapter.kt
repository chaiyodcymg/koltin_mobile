package com.pac.kotlin_mobile

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.MypostLayoutBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyPostAdapter(val items: ArrayList<Postlist>, val context: Context):
    RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {

    inner class ViewHolder(view: View ,val binding: MypostLayoutBinding) : RecyclerView.ViewHolder(view) {init {
//
//        binding.deletePost.setOnClickListener {
//            val myBuilder = AlertDialog.Builder(this)
//            myBuilder.apply {
//                setTitle("Title")
//                setMessage("คุณต้องการลบโพสต์ใช่ไหมคะ")
//                create()
//                setNegativeButton("Yes") {dialog, which ->
//                    Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_LONG).show()
//                }
//                setPositiveButton("No") {dialog, which -> dialog.cancel()}
//                show()
//            }
//            myBuilder.show()
//        }

        binding.editPost.setOnClickListener {

        }

//        binding.deletePost.setOnClickListener{
//            val myBuilder = AlertDialog.Builder(context)
//            myBuilder.apply {
//                setMessage("ลบ")
//                setNegativeButton("Yes") {dialog, which ->
//                    api.deletePost(itemId.toInt())
//                        .enqueue(object : Callback<Cat> {
//                            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
//                                if(response.isSuccessful) {
//                                    Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_LONG).show()
//                                }
//                            }
//
//                            override fun onFailure(call: Call<Cat>, t: Throwable) {
//                                Toast.makeText(context.applicationContext, "Faill Deleted", Toast.LENGTH_LONG).show()
//                            }
//                        })
//                }
//                setPositiveButton("No") {dialog, which -> dialog.cancel()}
//                show()
//            }
//        }

    } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostAdapter.ViewHolder {
        val binding = MypostLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)


        return ViewHolder(binding.root, binding)
    }



    override fun onBindViewHolder(holder: MyPostAdapter.ViewHolder, position: Int) {
        val binding = holder.binding

        binding.catName?.text = "ชื่อ : ${items[position].name}"
        binding.catColor?.text = "สี : ${items[position].color}"
        binding.catSpecies?.text = "สายพันธุ์ : ${items[position].species}"
        binding.catVacine?.text = "วัคซีนที่เคยได้รับ :  ${items[position].vaccine}"


        binding.deletePost.setOnClickListener{
           //pass the 'context' here

            var URL_API = URL.URL_API
            val myBuilder = AlertDialog.Builder(context)
            myBuilder.apply {
                setMessage("${items[position].id}")
                setNegativeButton("Yes") {dialog, which ->
                    val api: Cat_API = Retrofit.Builder()
                        .baseUrl(URL_API)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(Cat_API::class.java)
                    var id =  items[position].id

                    api.softdelete(id.toString().toInt())

                        .enqueue(object : Callback<Cat> {
                            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                                if(response.isSuccessful) {
                                    Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_LONG).show()
                                }
                            }

                            override fun onFailure(call: Call<Cat>, t: Throwable) {
                                Toast.makeText(context.applicationContext, "Faill Deleted", Toast.LENGTH_LONG).show()
                            }
                        })
                }
                setPositiveButton("No") {dialog, which -> dialog.cancel()}
                show()
            }

        }



    }

    override fun getItemCount(): Int {
        return items.size
    }
}

