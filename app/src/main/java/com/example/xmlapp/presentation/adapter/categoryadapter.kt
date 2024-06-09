package com.example.xmlapp.presentation.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlapp.R
import com.example.xmlapp.databinding.ViewholderCategoryBinding
import com.example.xmlapp.domain.model.CategoryX
import com.squareup.picasso.Picasso

class categoryadapter(val list :List<CategoryX>): RecyclerView.Adapter<categoryadapter.viewholder>() {

    private var selectedposi = -1
    private var clickListener:((String) -> Unit)? =null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val binding =
            ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

            val oldSelectedPosition = selectedposi
            selectedposi = holder.absoluteAdapterPosition
            notifyItemChanged(oldSelectedPosition)
            notifyItemChanged(selectedposi)
            clickListener?.invoke(category.strCategory)

        }


    }

    inner class viewholder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryX,int: Int) {

                binding.CLCategroryviewholder.setBackgroundColor(if(selectedposi!= int) ContextCompat.getColor(binding.root.context,R.color.md_theme_light_surface ) else ContextCompat.getColor(binding.root.context,R.color.md_theme_light_surfaceVariant) )

            binding.tvCategoryName.text = category.strCategory;
            Picasso.get().load(category.strCategoryThumb.toUri()).into(binding.IVCategoryimage)
        }

        fun onclick() {
            binding.CLCategroryviewholder.requestFocus()

        /*    binding.CLCategroryviewholder.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    androidx.appcompat.R.color.primary_dark_material_light
                )
            )*/
        }


    }
    fun setItemclicklistener(listener:(String) -> Unit){
          clickListener=listener
    }
}

