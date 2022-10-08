package com.pac.kotlin_mobile


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.SearchLayoutBinding


class SearchAdapter(val items: ArrayList<Search>, val context: Context,val requireActivity: SearchActivity,val inflater: LayoutInflater):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var URL_API = URL.URL_API
    inner class ViewHolder(view: View, val binding: SearchLayoutBinding) : RecyclerView.ViewHolder(view) {init {} }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding

        binding_holder.id.text = "รหัสแมว ${items[position].id}"
        binding_holder.name.text = "ชื่อ ${items[position].name}"
        binding_holder.species.text = "สายพันธุ์ ${items[position].species}"
        binding_holder.color.text = "สี ${items[position].color}"
        Glide.with(context).load(URL_API +items[position].image).into( binding_holder.catImg)
        binding_holder.cardView.setOnClickListener {
            val  intent : Intent
            if(items[position].type == 0){
                intent = Intent(requireActivity.applicationContext, DetailFindhouseActivity::class.java)
            }else{
                intent = Intent(requireActivity.applicationContext, DetailLostCatActivity::class.java)
            }

            intent.putExtra("search",SearchPacelable(
                items[position].id,
                items[position].name,
                items[position].gender,
                items[position].color,
                items[position].vaccine,
                items[position].date_vaccine,
                items[position].species,
                items[position].more_info,
                items[position].image,
                items[position].house_no,
                items[position].street,
                items[position].sub_district,
                items[position].district,
                items[position].province,
                items[position].post_address,
                if (items[position].date == null) "" else items[position].date,
                if( items[position].notice_point == null)  "" else items[position].notice_point,
                if(items[position].place_to_found  == null)  "" else items[position].place_to_found ,
                items[position].firstname,
                items[position].lastname,
                items[position].phone,
                items[position].email,
                items[position].line_id,
                items[position].facebook,
                items[position].type,
                items[position].status,
                items[position].user_id,


                ))
            requireActivity.startActivity(intent)

        }
        binding_holder.btnHome.setOnClickListener {

            val  intent : Intent
            if(items[position].type == 0){
                intent = Intent(requireActivity.applicationContext, DetailFindhouseActivity::class.java)
            }else{
                intent = Intent(requireActivity.applicationContext, DetailLostCatActivity::class.java)
            }

            intent.putExtra("search",SearchPacelable(
                items[position].id,
                items[position].name,
                items[position].gender,
                items[position].color,
                items[position].vaccine,
                items[position].date_vaccine,
                items[position].species,
                items[position].more_info,
                items[position].image,
                items[position].house_no,
                items[position].street,
                items[position].sub_district,
                items[position].district,
                items[position].province,
                items[position].post_address,
                if (items[position].date == null) "" else items[position].date,
                if( items[position].notice_point == null)  "" else items[position].notice_point,
                if(items[position].place_to_found  == null)  "" else items[position].place_to_found ,
                items[position].firstname,
                items[position].lastname,
                items[position].phone,
                items[position].email,
                items[position].line_id,
                items[position].facebook,
                items[position].type,
                items[position].status,
                items[position].user_id,


                ))
            requireActivity.startActivity(intent)

        }
    }



    override fun getItemCount(): Int {
        return items.size
    }

}
