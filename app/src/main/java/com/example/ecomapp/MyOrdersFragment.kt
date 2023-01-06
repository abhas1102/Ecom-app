package com.example.ecomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text


class MyOrdersFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val nameFromPlaceOrder = arguments?.getString("userName")
        val view = inflater.inflate(R.layout.fragment_my_orders, container, false)
        val orderUserName : TextView = view.findViewById(R.id.myordername)
        orderUserName.text = nameFromPlaceOrder

        return view
    }




}