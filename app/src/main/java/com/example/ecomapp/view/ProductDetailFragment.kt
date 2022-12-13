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
import java.util.*
import kotlin.collections.ArrayList


class ProductDetailFragment : Fragment() {
    lateinit var binding: FragmentProductDetailBinding

    //var quantityValue = 0
    private val viewmodel:MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       /// return inflater.inflate(R.layout.fragment_product_detail, container, false)
        binding = FragmentProductDetailBinding.inflate(inflater,container,false)
        // Log.d("PriceAmount", binding.priceAmount.text.toString())
        Log.d("Price", binding.price.toString())
        Log.d("Title", binding.titleDetail.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar:BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigation)
       // navBar.visibility = View.GONE


        binding.productDetails1 = arguments?.getParcelable("product")
        var productDetail = binding.productDetails1
        val currency:Currency = Currency.getInstance(Locale.getDefault())
        val symbol = currency.symbol
        binding.price.text = "$symbol ${productDetail?.price}"
        binding.lifecycleOwner = viewLifecycleOwner
      //  viewmodel.idsList.add(productDetail?.id)
      //  val array = viewmodel.idsList
        Log.d("List of ids",viewmodel.listIds.toString())






        // var priceAmount = binding.priceAmount.text.toString().toFloat()

      //  Log.d("PriceAmount", binding.priceAmount.toString())

        // Log.d("ProductTitle", binding.productDetails1.title)
            Log.d("id contain status", viewmodel.listIds.contains(productDetail?.id).toString())
            /*   if (productDetail!!.id !in viewmodel.listIds) {
                   viewmodel.updatedQuantityValue = 0
                   binding.quantityText.text = viewmodel.updatedQuantityValue.toString()
               } */
        if (productDetail!!.id in viewmodel.mapIdQuantity.keys) {
            // val value = viewmodel.mapIdQuantity.filterValues { it == productDetail.id }.keys
            var valueOfId = viewmodel.mapIdQuantity.getValue(productDetail.id)
            Log.d("value of id", valueOfId.toString())
            viewmodel.updatedQuantityValue = valueOfId

            binding.addButton.setOnClickListener {
                // Log.d("ProductDetail",initialValue.toString())


                if (productDetail == null) return@setOnClickListener
                //  viewmodel.quantityValue = viewmodel.quantityValue + 1
                //  viewmodel.updatedQuantityValue = viewmodel.quantityValue
                viewmodel.updatedQuantityValue += 1
                //   binding.totalPriceText.text = "Total price"
                binding.quantityText.text = viewmodel.updatedQuantityValue.toString()
                viewmodel.mapIdQuantity.put(
                    productDetail.id,
                    viewmodel.updatedQuantityValue
                )
                Log.d(
                    "map id quantity",
                    "${viewmodel.mapIdQuantity.keys}, ${viewmodel.mapIdQuantity.values}"
                )
                //  binding.priceAmount.text =
                //     (productDetail.price * viewmodel.quantityValue).toString()

            }
           binding.addCartButton.setOnClickListener {
               viewmodel.updatedQuantityValue += 1
               //   binding.totalPriceText.text = "Total price"
               binding.quantityText.text = viewmodel.updatedQuantityValue.toString()
               viewmodel.mapIdQuantity.put(
                   productDetail.id,
                   viewmodel.updatedQuantityValue
               )
           }
        }
        else{
            viewmodel.updatedQuantityValue = 0
            binding.addButton.setOnClickListener {
                // Log.d("ProductDetail",initialValue.toString())


                if (productDetail == null) return@setOnClickListener
                //  viewmodel.quantityValue = viewmodel.quantityValue + 1
                //  viewmodel.updatedQuantityValue = viewmodel.quantityValue
                viewmodel.updatedQuantityValue += 1
                //   binding.totalPriceText.text = "Total price"
                binding.quantityText.text = viewmodel.updatedQuantityValue.toString()
                viewmodel.mapIdQuantity.put(
                    productDetail.id,
                    viewmodel.updatedQuantityValue
                )
                Log.d(
                    "map id quantity",
                    "${viewmodel.mapIdQuantity.keys}, ${viewmodel.mapIdQuantity.values}"
                )
                //  binding.priceAmount.text =
                //     (productDetail.price * viewmodel.quantityValue).toString()
                binding.addCartButton.setOnClickListener {

                    if (productDetail == null) return@setOnClickListener
                    //  viewmodel.quantityValue = viewmodel.quantityValue + 1
                    //  viewmodel.updatedQuantityValue = viewmodel.quantityValue
                    viewmodel.updatedQuantityValue += 1
                    //   binding.totalPriceText.text = "Total price"
                    binding.quantityText.text = viewmodel.updatedQuantityValue.toString()
                    viewmodel.mapIdQuantity.put(
                        productDetail.id,
                        viewmodel.updatedQuantityValue
                    )
                }

            }

        }




       // viewmodel.quantityValue = 0
      //  Log.d("quantity value", viewmodel.quantityValue.toString())
        viewmodel.listIds.add(productDetail!!.id)


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

        binding.continueButton.setOnClickListener{
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
        Log.d("quantupdatelast", viewmodel.updatedQuantityValue.toString())
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
       // binding.quantityText.text = viewmodel.quantityValue.toString()
        Log.d("cartupdatequantonresume", viewmodel.updatedQuantityValue.toString())
        binding.quantityText.text = viewmodel.updatedQuantityValue.toString()
       // binding.priceAmount.text = (binding.productDetails1!!.price!! * viewmodel.quantityValue).toString()
        Toast.makeText(activity,"onResumeCalled",Toast.LENGTH_SHORT).show()
    }


    override fun onStop() {
        super.onStop()
       // (activity as AppCompatActivity?)!!.supportActionBar!!.show()
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