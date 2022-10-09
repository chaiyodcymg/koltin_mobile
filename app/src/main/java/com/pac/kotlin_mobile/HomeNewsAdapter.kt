package com.pac.kotlin_mobile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.HomeNewsLayoutBinding


class HomeNewsAdapter(val items: ArrayList<News>, val context: Context) :
    RecyclerView.Adapter<HomeNewsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: HomeNewsLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        init {
//            binding.cardView.setOnClickListener {
//                val item = items[adapterPosition]
//                Toast.makeText(context,"Click on news: ${item.title} Image: ${item.image} detail: ${item.detail} " +
//                        "user_id: ${item.user_id}",
//                    Toast.LENGTH_SHORT).show()
//            }
        }
    }


    var URL_API = URL.URL_API

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val   bindingnews = HomeNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingnews.root,bindingnews)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        binding_holder.newsTitle?.text = items[position].title
        Glide.with(context).load(items[position].image).into(binding_holder.newsImg)
//      Glide.with(context)
//          .load(URL_API +items[position].image)
//          .into( binding_holder.newsImg)

//        Glide.with(context).load(items[position].image).into(binding_holder.newsImg)

    }

    override fun getItemCount(): Int {
        return items.size
    }


}