package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivitySearchBinding
import com.pac.kotlin_mobile.databinding.FragmentDetailsBinding
import com.pac.kotlin_mobile.databinding.SearchLayoutBinding


class SearchAdapter(val items: ArrayList<Lostcat>, val context: Context,val requireActivity: SearchActivity,val inflater: LayoutInflater):
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

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}