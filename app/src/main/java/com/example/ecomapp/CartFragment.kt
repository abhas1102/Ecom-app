package com.example.ecomapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class CartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        val quantityText: String? = arguments?.getString("quantity_text")
        val quantityCart:TextView = view.findViewById(R.id.quantity_text)
        quantityCart.text = quantityText.toString()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(activity,"onDestroyCartCalled",Toast.LENGTH_SHORT).show()
    }



}