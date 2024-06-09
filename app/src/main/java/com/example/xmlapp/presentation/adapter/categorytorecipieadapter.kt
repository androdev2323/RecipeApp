package com.example.xmlapp.presentation.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlapp.databinding.ViewholderCategroyrecpiecardBinding
import com.example.xmlapp.domain.model.Meal
import com.squareup.picasso.Picasso

class categorytorecipieadapter(private val list :List<Meal>,private val onRecipieclicked:(Int) -> Unit): RecyclerView.Adapter<categorytorecipieadapter.viewholder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val binding =ViewholderCategroyrecpiecardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewholder(binding)
    }

    override fun getItemCount(): Int {

        return list.size
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val category = list[position]
        holder.bind(category,holder.absoluteAdapterPosition)

        holder.itemView.setOnClickListener {

           onRecipieclicked(category.idMeal.toInt())
        }




    }

    inner class viewholder(val binding: ViewholderCategroyrecpiecardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Meal, int: Int) {
            Picasso.get().load(category.strMealThumb.toUri()).into(binding.imageView)
            binding.chip.text=category.strMeal
            binding.TvRecepiename.text=category.strMeal

        }




    }
    /* a function whoes working will be implemented in the fragment and then this func will pass
    the working to the itemclick listner variable*/



}

