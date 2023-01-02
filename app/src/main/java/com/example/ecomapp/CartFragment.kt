package com.example.ecomapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.ecomapp.adapter.CartAdapter
import com.example.ecomapp.clicklistener.ClickListener
import com.example.ecomapp.databinding.CartItemLayoutBinding
import com.example.ecomapp.databinding.FragmentCartBinding
import com.example.ecomapp.databinding.FragmentHomeBinding
import com.example.ecomapp.model.CartProductDataModel
import com.example.ecomapp.model.ProductDataModel
import com.example.ecomapp.utils.bindImage
import com.example.ecomapp.viewmodel.MainViewModel
import org.w3c.dom.Text

class CartFragment : Fragment() {
   lateinit var binding: FragmentCartBinding
   private val viewModelForCart : MainViewModel by activityViewModels()
       // var cartProductList = ArrayList<CartProductDataModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false)

      //  val quantityText: String? = arguments?.getString("quantity_text")
       // val keysText = arguments?.getInt("product_id")
      //  Log.d("keystext",keysText.toString())
      //  val quantityCart:TextView = view.findViewById(R.id.quantity_text)
       // quantityCart.text = quantityText.toString()
      //  quantityCart.text = viewModelForCart.updatedQuantityValue.toString()
        // Receive I'd and quantity of product both in fragment cart through bundle
        // Check if id is clicked earlier, if clicked then just increase the quantity
        // If it is not clicked earlier then add another card into the list




        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // binding.cartProductDetail = arguments?.getParcelable("cartProductDetail")
       // var value = viewModelForCart.mapIdQuantity.values
       // val productId = arguments?.getInt("product_id")
        var cartProductList = arrayListOf<CartProductDataModel>()

       // val priceProduct = arguments?.getFloat("product_price")


        //Adding values saved in viewmodel map in cartProductList so that all adapter can fetch values one by one from list
        for (i in viewModelForCart.mapIdQuantity.values)  cartProductList.add(CartProductDataModel(i.first,"abcd",(i.first * i.second)))







        Log.d("cartArray", cartProductList.toString())
        binding.lifecycleOwner = viewLifecycleOwner
        binding.cartRecyclerView.adapter = CartAdapter(cartProductList)

        val itemUpdateListener = object: ClickListener{
            override fun onUpdate(cartItemProduct: CartProductDataModel) {


            }

            override fun onClick(itemProduct: ProductDataModel) {
                TODO("Not yet implemented")
            }
        }



    }
    fun setUpRecyclerView() {

    }




    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(activity,"onDestroyCartCalled",Toast.LENGTH_SHORT).show()
    }



}