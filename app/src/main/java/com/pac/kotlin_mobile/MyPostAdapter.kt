package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.MypostLayoutBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyPostAdapter(val items: ArrayList<Postlist>, val context: Context):
    RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {
    var URL_API = URL.URL_API
    inner class ViewHolder(view: View ,val binding: MypostLayoutBinding) : RecyclerView.ViewHolder(view) {init {


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
        binding.catInfo?.text = "ข้อมูลสัตว์ : ${items[position].more_info}"
        binding.catContact?.text = "ติดต่อ : ${items[position].firstname} " + "${items[position].lastname} "
        binding.catPhone?.text = "เบอร์โทร : ${items[position].phone}"
        Glide.with(context).load(URL_API + items[position].image).into(binding.imageCat)

        binding.deletePost.setOnClickListener{

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
                                    Toast.makeText(context, "Successfully Updated", Toast.LENGTH_LONG).show()
                                    remove(position)
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

        binding.editPost.setOnClickListener {
            Toast.makeText(context,"Click : ${items[position].id}",
                Toast.LENGTH_LONG).show()
            val post_id = items[position].id
            val intent = Intent(context, EditPostActivity::class.java)
            intent.putExtra("id", post_id.toString())
            intent.putExtra("namecat", items[position].name)
            intent.putExtra("gender", items[position].gender)
            intent.putExtra("color", items[position].color)
            intent.putExtra("vaccine", items[position].vaccine)
            intent.putExtra("date", items[position].date)
            intent.putExtra("species", items[position].species)
            intent.putExtra("more_info", items[position].more_info)
//            intent.putExtra("image", items[position].image)
            intent.putExtra("house_no", items[position].house_no)
            intent.putExtra("street", items[position].street)
            intent.putExtra("sub_district", items[position].sub_district)
            intent.putExtra("district", items[position].district)
            intent.putExtra("province", items[position].province)
            intent.putExtra("post_address", items[position].post_address)
            intent.putExtra("firstname", items[position].firstname)
            intent.putExtra("lastname", items[position].lastname)
            intent.putExtra("phone", items[position].phone)
            intent.putExtra("email", items[position].email)
            intent.putExtra("line_id", items[position].line_id)
            intent.putExtra("facebook", items[position].facebook)
            context.startActivity(intent)
        }

    }
    fun remove(index:Int){
        items.removeAt(index)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return items.size
    }



}


