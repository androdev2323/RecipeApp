package com.example.xmlapp.presentation.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlapp.domain.model.recepiedetail.Meal
import com.example.xmlapp.databinding.ViewholderRecipedetailBinding
import com.example.xmlapp.domain.model.recepiedetail.Mealingridient

class recipedetailadapter: ListAdapter<Mealingridient, recipedetailadapter.recipeadapterviewholder>(diffcallback()) {

    inner class recipeadapterviewholder(val binding: ViewholderRecipedetailBinding) :RecyclerView.ViewHolder(binding.root){
          fun bind(meal: Mealingridient){
              binding.TvIngredient.text=meal.ingredient.toString()
              binding.TvMeasure.text=meal.measure.toString()
              binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                  if(isChecked){
                      binding.TvMeasure.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
                      binding.TvIngredient.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
                  }
                  else{
                      binding.TvMeasure.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG.inv()
                      binding.TvIngredient.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG.inv()
                  }
              }

          }
    }
 class diffcallback():DiffUtil.ItemCallback<Mealingridient>() {



     override fun areItemsTheSame(oldItem: Mealingridient, newItem: Mealingridient): Boolean {
         return oldItem==newItem
     }

     override fun areContentsTheSame(oldItem: Mealingridient, newItem: Mealingridient): Boolean {
         return  oldItem==newItem
     }
 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recipeadapterviewholder {
        val binding=ViewholderRecipedetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return recipeadapterviewholder(binding)
    }

    override fun onBindViewHolder(holder: recipeadapterviewholder, position: Int) {
          val meal=getItem(position)
        holder.bind(meal)

    }
}