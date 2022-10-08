package com.pac.kotlin_mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.MoreFindhouseLayoutBinding
import com.pac.kotlin_mobile.databinding.MypostLayoutBinding

class CatFindhouseMoreAdapter(val items: ArrayList<Findhouse>, val context: Context):

    RecyclerView.Adapter<CatFindhouseMoreAdapter.ViewHolder>()  {
    inner class ViewHolder(view: View, val binding: MoreFindhouseLayoutBinding) : RecyclerView.ViewHolder(view) {init {


    } }
    var URL_API = URL.URL_API
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MoreFindhouseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        binding_holder.catName?.text = "ชื่อ:" + items[position].name
        Glide.with(context).load( URL_API + items[position].image).into(binding_holder.catImg)
        binding_holder.catColor?.text ="สี:" +  items[position].color
        binding_holder.catSpecies?.text = "สายพันธุ์:" + items[position].species

//        binding_holder.catFind.setOnClickListener {
//            var URL_API = URL.URL_API
//
//        }
//        binding_holder.btnHome.setOnClickListener {
//            var URL_API = URL.URL_API
//
//        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}