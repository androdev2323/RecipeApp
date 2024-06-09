package com.example.xmlapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.xmlapp.R
import com.example.xmlapp.databinding.FragmentOrderingmenuBinding
import com.example.xmlapp.databinding.FragmentRecpiedetailBinding
import com.example.xmlapp.presentation.viewmodels.orderingmenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class signupfragment:Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}