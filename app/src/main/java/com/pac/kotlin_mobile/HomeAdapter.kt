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
import com.pac.kotlin_mobile.databinding.FragmentHomeBinding

import com.pac.kotlin_mobile.databinding.FragmentNewsBinding
import com.pac.kotlin_mobile.databinding.HomeNewsLayoutBinding
import com.pac.kotlin_mobile.databinding.SearchLayoutBinding



class HomeAdapter(val items: ArrayList<News>, val context: Context,val requireActivity: MainActivity,val inflater: LayoutInflater) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: HomeNewsLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        init {
            binding.cardView.setOnClickListener {
                val item = items[adapterPosition]
                Toast.makeText(context,"Click on news: ${item.title} Image: ${item.image} detail: ${item.detail} " +
                        "user_id: ${item.user_id}",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val   bindingnews = HomeNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingnews.root,bindingnews)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    var URL_API = URL.URL_API
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding

        binding_holder.newsTitle?.text = items[position].title
        Glide.with(context).load(URL_API + items[position].image).into(binding_holder.newsImg)

        binding_holder.cardView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("Home", "Home")
            bundle.putString("title", items[position].title)
            bundle.putString("image",items[position].image)
            bundle.putString("detail",items[position].detail)
            var  binding : FragmentHomeBinding
            binding = FragmentHomeBinding.inflate(inflater)
            val new = news_info()
            new.arguments = bundle
            val transaction = requireActivity.supportFragmentManager.beginTransaction()
            transaction.replace(binding.frameLayout.id, new)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }




}