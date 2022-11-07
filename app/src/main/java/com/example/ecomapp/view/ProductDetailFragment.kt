package com.example.ecomapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ecomapp.CartFragment
import com.example.ecomapp.R
import com.example.ecomapp.databinding.FragmentProductDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProductDetailFragment : Fragment() {
    lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       /// return inflater.inflate(R.layout.fragment_product_detail, container, false)

        binding = FragmentProductDetailBinding.inflate(inflater,container,false)
        binding.productDetails1 = arguments?.getParcelable("product")
        var productDetail = binding.productDetails1
        var quantityValue = 0
       // var priceAmount = binding.priceAmount.text.toString().toFloat()

        Log.d("PriceAmount", binding.priceAmount.text.toString())
       // Log.d("ProductTitle", binding.productDetails1.title)
        binding.addButton.setOnClickListener {
           // Log.d("ProductDetail",initialValue.toString())
            if (productDetail == null) return@setOnClickListener
            quantityValue = quantityValue + 1
            binding.totalPriceText.text = "Total price"
            binding.quantityText.text = quantityValue.toString()
            binding.priceAmount.text = (productDetail.price*quantityValue).toString()


           // Log.d("ProductDetail",priceAmount.toString())
             }
        binding.removeButton.setOnClickListener {
            if (quantityValue != 0) {
                if (productDetail == null) return@setOnClickListener
                quantityValue = quantityValue -1
                binding.quantityText.text = quantityValue.toString()
             //   binding.priceAmount.text = (priceAmount*quantityValue).toString()
                binding.priceAmount.text = (productDetail.price*quantityValue).toString()

            }
        }
        binding.addCartButton.setOnClickListener{

            val cartFragment = CartFragment()


            val ft = fragmentManager?.beginTransaction()
            ft?.replace(R.id.flFragment,cartFragment)
            ft?.addToBackStack("")
            ft?.commit()
            // Create the transaction
            // Create the transaction
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar:BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigation)
        navBar.visibility = View.GONE




      //  binding.productDetails = viewModel.products.value!!.get(0)
      //  binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}