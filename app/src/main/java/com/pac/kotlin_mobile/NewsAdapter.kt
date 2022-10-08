package com.pac.kotlin_mobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAdapter(val items: ArrayList<News>, val context: Context,val requireActivity: MainActivity,val inflater: LayoutInflater,) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    inner class ViewHolder(view: View, val binding: NewsLayoutBinding ) :
        RecyclerView.ViewHolder(view) {
        init {
            binding.cardView.setOnClickListener {
                val item = items[adapterPosition]
                Toast.makeText(context,"Click on news: ${item.title} Image: ${item.image} detail: ${item.detail} " +
                        "user_id: ${item.user_id}",
                    Toast.LENGTH_SHORT).show()
            }
            binding.editNews.setOnClickListener{
                val item = items[adapterPosition]
                val contextView : Context = view.context
                val  intent = Intent(requireActivity.applicationContext, EditNewsActivity::class.java)
                intent.putExtra("id",item.id.toString())
                intent.putExtra("title",item.title)
                intent.putExtra("image",item.image)
                intent.putExtra("detail",item.detail)
                contextView.startActivity(intent)
            }


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val   bindingnews = NewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingnews.root,bindingnews)
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
        binding_holder.deleteNews.setOnClickListener{
            //pass the 'context' here

            var URL_API = URL.URL_API
            var  binding : FragmentNewsBinding
            binding = FragmentNewsBinding.inflate(inflater)
            val myBuilder = AlertDialog.Builder(context)
            myBuilder.apply {
                setMessage("delete : ${items[position].title} ?")
                setNegativeButton("Yes") {dialog, which ->
                    val api: NewsAPI = Retrofit.Builder()
                        .baseUrl(URL_API)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(NewsAPI::class.java)
                    var id =  items[position].id

                    api.softdeletenews(id.toString().toInt())
                        .enqueue(object : Callback<News> {

                            override fun onResponse(call: Call<News>, response: Response<News>) {
                                if(response.isSuccessful) {
                                    Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_LONG).show()
                                    remove(position)
                                }
                            }

                            override fun onFailure(call: Call<News>, t: Throwable) {
                                Toast.makeText(context.applicationContext, "Fail Deleted", Toast.LENGTH_LONG).show()
                            }
                        })
                }
                setPositiveButton("No") {dialog, which -> dialog.cancel()}
                show()
            }

        }




    }
    fun remove(index:Int){
        items.removeAt(index)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
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