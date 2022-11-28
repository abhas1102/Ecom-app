package com.example.ecomapp.view

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.example.ecomapp.CartFragment
import com.example.ecomapp.R
import com.example.ecomapp.databinding.FragmentProductDetailBinding
import com.example.ecomapp.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProductDetailFragment : Fragment() {
    lateinit var binding: FragmentProductDetailBinding
    //var quantityValue = 0
    private val viewmodel:MainViewModel by activityViewModels()




   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity,"onCreateCalled",Toast.LENGTH_SHORT).show()
        if(savedInstanceState == null) {
            quantityValue = 0
        } else {
            quantityValue = savedInstanceState.getInt("quantity",0)
        }
       // if (savedInstanceState!=null) quantityValue = savedInstanceState.getInt("quantity",0)

    } */


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       /// return inflater.inflate(R.layout.fragment_product_detail, container, false)


        binding = FragmentProductDetailBinding.inflate(inflater,container,false)
      //  binding.priceAmount.text = binding.productDetails1!!.price.toString()



       // var priceAmount = binding.priceAmount.text.toString().toFloat()

        Log.d("PriceAmount", binding.priceAmount.text.toString())

       // Log.d("ProductTitle", binding.productDetails1.title)

                // Log.d("ProductDetail",initialValue.toString())




                // Log.d("ProductDetail",priceAmount.toString())

          /*  binding.removeButton.setOnClickListener {
                if (quantityValue != 0) {
                    if (productDetail == null) return@setOnClickListener
                    quantityValue = quantityValue - 1
                    binding.quantityText.text = quantityValue.toString()
                    //   binding.priceAmount.text = (priceAmount*quantityValue).toString()
                    binding.priceAmount.text = (productDetail.price * quantityValue).toString()

                }
            } */



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar:BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigation)
        navBar.visibility = View.GONE

        binding.productDetails1 = arguments?.getParcelable("product")
        var productDetail = binding.productDetails1
        binding.lifecycleOwner = viewLifecycleOwner



        // var priceAmount = binding.priceAmount.text.toString().toFloat()

        Log.d("PriceAmount", binding.priceAmount.text.toString())

        // Log.d("ProductTitle", binding.productDetails1.title)
        binding.addButton.setOnClickListener {
            // Log.d("ProductDetail",initialValue.toString())

            if (productDetail == null) return@setOnClickListener
            viewmodel.quantityValue = viewmodel.quantityValue + 1
            binding.totalPriceText.text = "Total price"
            binding.quantityText.text = viewmodel.quantityValue.toString()
            binding.priceAmount.text =
                (productDetail.price * viewmodel.quantityValue).toString()

        }


        // Log.d("ProductDetail",priceAmount.toString())

        /*  binding.removeButton.setOnClickListener {
              if (quantityValue != 0) {
                  if (productDetail == null) return@setOnClickListener
                  quantityValue = quantityValue - 1
                  binding.quantityText.text = quantityValue.toString()
                  //   binding.priceAmount.text = (priceAmount*quantityValue).toString()
                  binding.priceAmount.text = (productDetail.price * quantityValue).toString()

              }
          } */

        binding.addCartButton.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("quantity_text", String.format("%d",viewmodel.quantityValue))
            val cartFragment = CartFragment()
            cartFragment.arguments = bundle


            val ft = fragmentManager?.beginTransaction()
            ft?.replace(R.id.flFragment,cartFragment)
            ft?.addToBackStack("")
            ft?.commit()
            // Create the transaction
            // Create the transaction
        }
        Toast.makeText(activity,"onCreateViewCalled",Toast.LENGTH_SHORT).show()




      //  binding.productDetails = viewModel.products.value!!.get(0)
      //  binding.lifecycleOwner = viewLifecycleOwner
    }
   /* override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("quantity", quantityValue)

    } */




    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding.quantityText.text = viewmodel.quantityValue.toString()
        binding.priceAmount.text = (binding.productDetails1!!.price!! * viewmodel.quantityValue).toString()
        Toast.makeText(activity,"onResumeCalled",Toast.LENGTH_SHORT).show()
    }


    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        Toast.makeText(activity,"onStopCalled",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(activity,"onPauseCalled",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(activity,"onDestroyViewCalled",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(activity,"onDestroyCalled",Toast.LENGTH_SHORT).show()
    }






}