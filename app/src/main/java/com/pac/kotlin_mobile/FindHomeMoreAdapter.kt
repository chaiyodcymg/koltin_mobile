package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.URL.URL_API
import com.pac.kotlin_mobile.databinding.CatfindhomeMoreLayoutBinding
import com.pac.kotlin_mobile.databinding.FragmentCatfindhomeMoreBinding
import com.pac.kotlin_mobile.databinding.FragmentNewsBinding
import com.pac.kotlin_mobile.databinding.NewsLayoutBinding
import com.pac.kotlin_mobile.databinding.NewsLayoutBinding.inflate


class FindHomeMoreAdapter(val items: ArrayList<Findhouse>, val context: Context, val requireActivity: MainActivity, val inflater: LayoutInflater) :
    RecyclerView.Adapter<FindHomeMoreAdapter.ViewHolder>() {
    class ViewHolder (view: View, val binding: CatfindhomeMoreLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        init {
//            binding.cardView.setOnClickListener {
//                val item = items[adapterPosition]
//                Toast.makeText(context,"Click on news: ${item.title} Image: ${item.image} detail: ${item.detail} " +
//                        "user_id: ${item.user_id}",
//                    Toast.LENGTH_SHORT).show()
//            }
//            binding.editNews.setOnClickListener{
//                val item = items[adapterPosition]
//                val contextView : Context = view.context
//                val  intent = Intent(requireActivity.applicationContext, EditNewsActivity::class.java)
//                intent.putExtra("id",item.id.toString())
//                intent.putExtra("title",item.title)
//                intent.putExtra("image",item.image)
//                intent.putExtra("detail",item.detail)
//                contextView.startActivity(intent)
//            }
            binding.catFind.setOnClickListener {
                val intent = Intent(view.context, DetailFindhouseActivity::class.java)
                view.context.startActivity(intent)
            }
//            binding.btnHome.setOnClickListener {
//                var detailfragment: Fragment =  FragmentDetail()
//
//                replaceFragment(detailfragment)
//            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val   bindingnews = CatfindhomeMoreLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingnews.root,bindingnews)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        binding_holder.idCat?.text = "รหัสแมว " + items[position].id.toString()
        binding_holder.colorCat?.text ="สี " + items[position].color
        binding_holder.speciesCat?.text ="สายพันธ์ " + items[position].species
        Glide.with(context).load(URL_API +items[position].image).into( binding_holder.catImg)
        binding_holder.catFind.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("id", items[position].id.toString())
            bundle.putString("name",items[position].name)
            bundle.putString("gender",items[position].gender)
            bundle.putString("species",items[position].species)
            bundle.putString("vaccine",items[position].vaccine)
            bundle.putString("date_vaccine",items[position].date_vaccine)
            bundle.putString("more_info",items[position].more_info)
            bundle.putString("image",items[position].image)
            bundle.putString("house_no",items[position].house_no)
            bundle.putString("street",items[position].street)
            bundle.putString("sub_district",items[position].sub_district)
            bundle.putString("district",items[position].district)
            bundle.putString("province",items[position].province)
            bundle.putString("post_address",items[position].post_address)
            bundle.putString("firstname",items[position].firstname)
            bundle.putString("lastname",items[position].lastname)
            bundle.putString("phone",items[position].phone)
            bundle.putString("email",items[position].email)
            bundle.putString("line_id",items[position].line_id)
            bundle.putString("facebook",items[position].facebook)

            var  binding : FragmentCatfindhomeMoreBinding
            binding = FragmentCatfindhomeMoreBinding.inflate(inflater)
            val new = FragmentDetail()
            new.arguments = bundle

            val transaction = requireActivity.supportFragmentManager.beginTransaction()
            transaction.replace(binding.frameLayout.id, new)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}