package com.pac.kotlin_mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pac.kotlin_mobile.databinding.MypostLayoutBinding


class MyPostAdapter(val items: ArrayList<Post>, val context: Context):
    RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {

    inner class ViewHolder(view: View ,val binding: MypostLayoutBinding) : RecyclerView.ViewHolder(view) {init {} }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MypostLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        binding.catName.text = "ชื่อ : ${items[position].name}"
        binding.catColor.text = "สี : ${items[position].color}"
        binding.catSpecies.text = "สายพันธุ์ : ${items[position].species}"
        binding.catSpecies.text = "วัคซีนที่เคยได้รับ :  ${items[position].vaccined}"


    }

    override fun getItemCount(): Int {
        return items.size
    }
}

