package com.pac.kotlin_mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.pac.kotlin_mobile.databinding.CheckPostLayoutBinding
import com.pac.kotlin_mobile.databinding.MypostLayoutBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CheckPostAdapter(val items: ArrayList<Postlist>, val context: Context):
    RecyclerView.Adapter<CheckPostAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: CheckPostLayoutBinding) : RecyclerView.ViewHolder(view) {init {


    } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CheckPostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding


        binding.catName?.text = "ชื่อ : ${items[position].name}"
        binding.catColor?.text = "สี : ${items[position].color}"
        binding.catSpecies?.text = "สายพันธุ์ : ${items[position].species}"
        binding.catVacine?.text = "วัคซีนที่เคยได้รับ :  ${items[position].vaccine}"
        binding.catInfo?.text = "ข้อมูลสัตว์ : ${items[position].more_info}"
        binding.catContact?.text = "ติดต่อ : ${items[position].firstname} " + "${items[position].lastname} "
        binding.catPhone?.text = "เบอร์โทร : ${items[position].phone}"


        binding.denyPost.setOnClickListener{

            var URL_API = URL.URL_API
            val myBuilder = AlertDialog.Builder(context)
            myBuilder.apply {
                setMessage("ปฏิเสธโพสต์ ${items[position].id}," + "ชื่อ: ${items[position].name}")
                setNegativeButton("Yes") {dialog, which ->
                    val api: Cat_API = Retrofit.Builder()
                        .baseUrl(URL_API)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(Cat_API::class.java)
                    var id =  items[position].id
                    api.deny_post(id.toString().toInt())
                        .enqueue(object : Callback<Cat> {
                            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                                if(response.isSuccessful) {
                                    Toast.makeText(context, "Successfully Updated", Toast.LENGTH_LONG).show()

                                }
                            }

                            override fun onFailure(call: Call<Cat>, t: Throwable) {
                                Toast.makeText(context.applicationContext, "Faill Updated", Toast.LENGTH_LONG).show()
                            }
                        })
                }
                setPositiveButton("No") {dialog, which -> dialog.cancel()}
                show()
            }

        }

        binding.acceptPost.setOnClickListener{

            var URL_API = URL.URL_API
            val myBuilder = AlertDialog.Builder(context)
            myBuilder.apply {
                setMessage("ยอมรับโพสต์ ${items[position].id}," + "ชื่อ: ${items[position].name}")
                setNegativeButton("Yes") {dialog, which ->
                    val api: Cat_API = Retrofit.Builder()
                        .baseUrl(URL_API)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(Cat_API::class.java)
                    var id =  items[position].id
                    api.accept_post(id.toString().toInt())
                        .enqueue(object : Callback<Cat> {
                            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                                if(response.isSuccessful) {
                                    Toast.makeText(context, "Successfully Updated", Toast.LENGTH_LONG).show()

                                }
                            }

                            override fun onFailure(call: Call<Cat>, t: Throwable) {
                                Toast.makeText(context.applicationContext, "Faill Updated", Toast.LENGTH_LONG).show()
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