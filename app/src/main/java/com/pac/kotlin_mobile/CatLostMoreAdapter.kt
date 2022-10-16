package com.pac.kotlin_mobile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.FragmentMissingCatBinding
import com.pac.kotlin_mobile.databinding.HomeCatlostLayoutBinding
import com.pac.kotlin_mobile.databinding.MoreFindhouseLayoutBinding

class CatLostMoreAdapter(val items: ArrayList<Lostcat>, val context: Context, val requireActivity: MainActivity, val inflater: LayoutInflater) :
    RecyclerView.Adapter<CatLostMoreAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: HomeCatlostLayoutBinding) :
        RecyclerView.ViewHolder(view) {init {


    }
    }

    var URL_API = URL.URL_API
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingcatlost =
            HomeCatlostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingcatlost.root, bindingcatlost)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        binding_holder.catName?.text = "ชื่อ:" + items[position].name
        Glide.with(context).load(URL_API + items[position].image)
            .into(binding_holder.catHomelessImg)
        binding_holder.catColor?.text = "สี:" + items[position].color
        binding_holder.catSpecies?.text = "สายพันธุ์:" + items[position].species
        val bundle = Bundle()
        bundle.putString("id", items[position].id.toString())
        bundle.putString("name", items[position].name)
        bundle.putString("gender", items[position].gender)
        bundle.putString("species", items[position].species)
        bundle.putString("vaccine", items[position].vaccine)
        bundle.putString("date_vaccine", items[position].date_vaccine)
        bundle.putString("more_info", items[position].more_info)
        bundle.putString("image", items[position].image)
        bundle.putString("house_no", items[position].house_no)
        bundle.putString("street", items[position].street)
        bundle.putString("sub_district", items[position].sub_district)
        bundle.putString("district", items[position].district)
        bundle.putString("province", items[position].province)
        bundle.putString("post_address", items[position].post_address)
        bundle.putString("date", items[position].date)
        bundle.putString("notice_point", items[position].notice_point)
        bundle.putString("place_to_found", items[position].place_to_found)
        bundle.putString("firstname", items[position].firstname)
        bundle.putString("lastname", items[position].lastname)
        bundle.putString("phone", items[position].phone)
        bundle.putString("email", items[position].email)
        bundle.putString("line_id", items[position].line_id)
        bundle.putString("facebook", items[position].facebook)

        binding_holder.catHomelessBlock.setOnClickListener {
            var binding: FragmentMissingCatBinding
            binding = FragmentMissingCatBinding.inflate(inflater)
            val new = FragmentDetailLost()
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