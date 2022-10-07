package com.pac.kotlin_mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.HomeFindhouseLayoutBinding
import com.pac.kotlin_mobile.databinding.HomeNewsLayoutBinding


class HomeFindhouseAdapter(val items: ArrayList<Findhouse>, val context: Context, val requireActivity: MainActivity, val inflater: LayoutInflater) :
    RecyclerView.Adapter<HomeFindhouseAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: HomeFindhouseLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        init {
            binding.catFindhomeBlock.setOnClickListener {
                val item = items[adapterPosition]
                Toast.makeText(context,"Click on pet name: ${item.name} Id: ${item.id} gender: ${item.gender} " +
                        "user_id: ${item.user_id}",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    var URL_API = URL.URL_API
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val   bindingfind = HomeFindhouseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingfind.root,bindingfind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        binding_holder.catName?.text = "ชื่อ:" + items[position].name
        Glide.with(context).load( URL_API + items[position].image).into(binding_holder.catImg)
        binding_holder.catColor?.text ="สี:" +  items[position].color
        binding_holder.catSpecies?.text = "สายพันธุ์:" + items[position].species
    }

    override fun getItemCount(): Int {
        return items.size
    }
}