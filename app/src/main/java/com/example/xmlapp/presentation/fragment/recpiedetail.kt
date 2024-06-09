package com.example.xmlapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlapp.Di.NetworkResponse
import com.example.xmlapp.R
import com.example.xmlapp.databinding.FragmentRecpiedetailBinding
import com.example.xmlapp.domain.model.recepiedetail.Meal
import com.example.xmlapp.domain.model.recepiedetail.Mealingridient
import com.example.xmlapp.presentation.adapter.recipedetailadapter
import com.example.xmlapp.presentation.viewmodels.orderingmenuViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class recpiedetail : Fragment() {
    private lateinit var binding: FragmentRecpiedetailBinding
    private val  viewmodel: orderingmenuViewModel by viewModels()
    private val args:recpiedetailArgs by navArgs()
    lateinit var recipedetailadapter: recipedetailadapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_recpiedetail,container,false)
        recipedetailadapter= recipedetailadapter()
        observedata()
        return binding.root
    }

    fun observedata(){
Log.d("Details",args.recipeId.toString())

        lifecycleScope.launch {
            viewmodel.recipedetail.collect() {state->
              when(state){
                  is NetworkResponse.Error -> Unit
                  is NetworkResponse.Successfull -> {
                      Log.d("Details","We reached the response success")
                    var data=  convertdata(state.data!!)
                      recipedetailadapter.submitList(data)
                      setlayout(state.data)



                  }
                  is NetworkResponse.loading -> Unit
              }

            }
        }
    }

    fun setlayout(list:List<Meal>){
       binding.TvRecepiename.text=list[0].strMeal

        Picasso.get().load(list[0].strMealThumb!!.toUri()).into(binding.ivRecepieimage)
        binding.btCartminus.setOnClickListener(){
            binding.TvCartno.text=(binding.TvCartno.text.toString().toInt() -1).toString()
        }
        binding.btCartplus.setOnClickListener(){
            binding.TvCartno.text=(binding.TvCartno.text.toString().toInt() + 1).toString()
        }
        binding.rvIngredients.rvIngredients.adapter=recipedetailadapter
        binding.rvIngredients.rvIngredients.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }
    private fun convertdata(data:List<Meal>):List<Mealingridient>{
        val ingredients= listOfNotNull<String>(
            data[0].strIngredient1,
            data[0].strIngredient2,
            data[0].strIngredient3,
            data[0].strIngredient4,
            data[0].strIngredient5,
            data[0].strIngredient6,
            data[0].strIngredient7,
            data[0].strIngredient8,
            data[0].strIngredient9,
            data[0].strIngredient10,
            data[0].strIngredient11,
            data[0].strIngredient12,
            data[0].strIngredient13,
            data[0].strIngredient14,
            data[0].strIngredient15,
            data[0].strIngredient16,
            data[0].strIngredient17,
            data[0].strIngredient18,
            data[0].strIngredient19,
            data[0].strIngredient20,
            )
        val measures= listOfNotNull<String>(
            data[0].strMeasure1,
            data[0].strMeasure2,
            data[0].strMeasure3,
            data[0].strMeasure4,
            data[0].strMeasure5,
            data[0].strMeasure6,
            data[0].strMeasure7,
            data[0].strMeasure8,
            data[0].strMeasure9,
            data[0].strMeasure10,
            data[0].strMeasure11,
            data[0].strMeasure12,
            data[0].strMeasure13,
            data[0].strMeasure14,
            data[0].strMeasure15,
            data[0].strMeasure16,
            data[0].strMeasure17,
            data[0].strMeasure18,
            data[0].strMeasure19,
            data[0].strMeasure20,
            )
   return ingredients.zip(measures){ingredients,measures ->
            Mealingridient(ingredients,measures)

        }.filter { it.ingredient.toString().isNotBlank() && it.measure.toString().isNotBlank() }
    }


}