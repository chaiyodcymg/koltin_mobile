package com.pac.kotlin_mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.HomeCatlostLayoutBinding


class HomeCatlostAdapter(val items: ArrayList<Lostcat>, val context: Context, val requireActivity: MainActivity, val inflater: LayoutInflater) :
    RecyclerView.Adapter<HomeCatlostAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: HomeCatlostLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        init {
            binding.catHomelessBlock.setOnClickListener {
                val item = items[adapterPosition]
                Toast.makeText(context,"Click on name: ${item.name} Id: ${item.id} color: ${item.color} " +
                        "user_id: ${item.user_id}",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    var URL_API = URL.URL_API
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val   bindingcatlost = HomeCatlostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingcatlost.root,bindingcatlost)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        binding_holder.catName?.text = items[position].name
        Glide.with(context).load( URL_API + items[position].image).into(binding_holder.catHomelessImg)
        binding_holder.catColor?.text ="สี:" +  items[position].color
        binding_holder.catSpecies?.text = "สายพันธุ์:" + items[position].species
    }

    override fun getItemCount(): Int {
        return items.size
    }
}