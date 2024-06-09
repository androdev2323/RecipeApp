package com.example.xmlapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlapp.Di.NetworkResponse
import com.example.xmlapp.R
import com.example.xmlapp.databinding.FragmentOrderingmenuBinding
import com.example.xmlapp.domain.model.CategoryX
import com.example.xmlapp.presentation.adapter.categoryadapter
import com.example.xmlapp.presentation.adapter.categorytorecipieadapter
import com.example.xmlapp.presentation.viewmodels.latestcategroyryuistate
import com.example.xmlapp.presentation.viewmodels.latestcategroyryuistate2
import com.example.xmlapp.presentation.viewmodels.orderingmenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class orderingmenufragment:Fragment() {
    private lateinit var binding: FragmentOrderingmenuBinding
    private val viewmodel:orderingmenuViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_orderingmenu,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewmodel.categorylist.collect() {state->
             when(state){
                 is latestcategroyryuistate.Success -> showrecipelist(state.catgory)
                 is latestcategroyryuistate.Error -> TODO()
               is   latestcategroyryuistate.loadingstate -> binding.progressbarCategory.visibility=View.VISIBLE
             }
        }


    }



    }
    fun showrecipelist(list: List<CategoryX>){
        binding.progressbarCategory.visibility=View.INVISIBLE
        val adapter=categoryadapter(list)

        binding.rvCategory.adapter=adapter
        adapter.setItemclicklistener {category ->
            viewmodel.getrecpielist(category)
            lifecycleScope.launch {
                viewmodel.recpielist.collect() {state->
                    when(state){
                        is latestcategroyryuistate2.loadingstate -> binding.pbresturant.visibility=View.VISIBLE
                        is latestcategroyryuistate2.Success -> {
                            binding.pbresturant.visibility=View.INVISIBLE
                            val adapter=categorytorecipieadapter(state.catgory,::observerecipedetails)
                            binding.rvResturant.adapter=adapter
                            binding.rvResturant.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                        }

                        else -> {}
                    }

                }
            }
        }
        binding.rvCategory.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

    }

    fun observerecipedetails(recipeid:Int){
        viewmodel.getrecipedetails(recipeid)
        val action=orderingmenufragmentDirections.actionOrderingmenufragment2ToRecpiedetail(recipeid)
        findNavController().navigate(action)
    }




}