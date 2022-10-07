package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.HomeNewsLayoutBinding



class HomeNewsAdapter(val items: ArrayList<Profile>, val context: Context):
    RecyclerView.Adapter<HomeNewsAdapter.ViewHolder>() {
    var URL_API = URL.URL_API
    inner class ViewHolder(view: View, val binding: HomeNewsLayoutBinding) : RecyclerView.ViewHolder(view) {init {} }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeNewsLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        binding_holder.newsTitle.text = items[position].email
        Glide.with(context).load(URL_API +items[position].image_profile).into( binding_holder.newsImg)

    }

    override fun getItemCount(): Int {
        return items.size
    }
}