package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pac.kotlin_mobile.databinding.SearchLayoutBinding


class SearchAdapter(val items :List<Search>, val context: Context):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(view: View, val binding: SearchLayoutBinding) : RecyclerView.ViewHolder(view) {init {} }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding

      binding_holder.tvID.text =  items[position].firstname
        binding_holder.tvName.text = items[position].lastname
    }

    override fun getItemCount(): Int {
        return items.size
    }
}