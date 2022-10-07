package com.pac.kotlin_mobile

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pac.kotlin_mobile.databinding.NewsLayoutBinding


class EditNewsAdapter(val items : List<News>, val context: Context) :
    RecyclerView.Adapter<EditNewsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: NewsLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        init {
//            binding.btnEdit.setOnClickListener {
//                val item = items[adapterPosition]
//                Toast.makeText(context,"Click on news: ${item.title} Image: ${item.image} detail: ${item.detail} " +
//                        "user_id: ${item.user_id}",
//                    Toast.LENGTH_SHORT).show()
//
//                val contextView : Context = view.context
//
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}