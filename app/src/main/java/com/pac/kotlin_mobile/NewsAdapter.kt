package com.pac.kotlin_mobile

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.FragmentNewsBinding
import com.pac.kotlin_mobile.databinding.FragmentNewsInfoBinding
import com.pac.kotlin_mobile.databinding.NewsLayoutBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class NewsAdapter(val items: List<News>, val context: Context,val requireActivity: MainActivity,val inflater: LayoutInflater) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    inner class ViewHolder(view: View, val binding: NewsLayoutBinding) :
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val   bindingnews = NewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingnews.root,bindingnews)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        binding_holder.newsTitle?.text = items[position].title
        Glide.with(context).load(items[position].image).into(binding_holder.newsImg)
        binding_holder.cardView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("title", items[position].title)
            bundle.putString("image",items[position].image)
            bundle.putString("detail",items[position].detail)
            var  binding : FragmentNewsBinding
            binding = FragmentNewsBinding.inflate(inflater)
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
//class NewsAdapter(val items :List<Search>, val context: Context):
//    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
//
//    inner class ViewHolder(view: View, val binding: FragmentNewsBinding) : RecyclerView.ViewHolder(view) {init {} }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = FragmentNewsBinding.inflate(
//            LayoutInflater.from(parent.context), parent,
//            false)
//        return ViewHolder(binding.root, binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val binding_holder = holder.binding

//        binding_holder.tvID.text =  items[position].firstname
//        binding_holder.tvName.text = items[position].lastname
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//}