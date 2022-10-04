package com.pac.kotlin_mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pac.kotlin_mobile.databinding.FragmentNewsBinding


//class NewsAdapter(val items :List<News>, val context: Context):
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
//
//        binding_holder.newstitle.text =  items[position].
//        binding_holder.news_info.text = items[position].lastname
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//}