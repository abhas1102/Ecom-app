package com.example.ecomapp

import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import com.example.ecomapp.adapter.CartAdapter
import com.example.ecomapp.clicklistener.ClickListener
import com.example.ecomapp.databinding.CartItemLayoutBinding
import com.example.ecomapp.databinding.FragmentCartBinding
import com.example.ecomapp.databinding.FragmentHomeBinding
import com.example.ecomapp.db.Entity
import com.example.ecomapp.db.ItemDao
import com.example.ecomapp.db.ItemRoomDatabase
import com.example.ecomapp.model.CartProductDataModel
import com.example.ecomapp.model.ProductDataModel
import com.example.ecomapp.utils.bindImage
import com.example.ecomapp.view.PlaceOrderFragment
import com.example.ecomapp.viewmodel.MainViewModel
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.util.*

class CartFragment : Fragment() {
   lateinit var binding: FragmentCartBinding
   private val viewModelForCart : MainViewModel by activityViewModels()
       // var cartProductList = ArrayList<CartProductDataModel>()

    val items = arrayListOf<Entity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


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

       /* if (viewModelForCart.mapIdQuantity.size == 0) {
            binding.addItemsInCart.visibility = View.VISIBLE
            binding.btnContinueCheckout.setBackgroundColor(Color.GRAY)
            binding.btnContinueCheckout.gravity = Gravity.BOTTOM
            binding.btnContinueCheckout.isEnabled = false

        } else binding.addItemsInCart.visibility = View.GONE  */

       /* if (items.size == 0) {
            binding.addItemsInCart.visibility = View.VISIBLE
            binding.btnContinueCheckout.setBackgroundColor(Color.GRAY)
            binding.btnContinueCheckout.gravity = Gravity.BOTTOM
            binding.btnContinueCheckout.isEnabled = false

        } else{
            binding.addItemsInCart.visibility = View.GONE
            binding.btnContinueCheckout.isEnabled = true
        } */


        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // binding.cartProductDetail = arguments?.getParcelable("cartProductDetail")
       // var value = viewModelForCart.mapIdQuantity.values
       // val productId = arguments?.getInt("product_id")
       // var cartProductList = arrayListOf<CartProductDataModel>()
        var cartProductsDbList = arrayListOf<Entity>()
        val currency: Currency = Currency.getInstance(Locale.getDefault())
        val symbol = currency.symbol
        val itemDb:ItemRoomDatabase = ItemRoomDatabase.getDatabase(requireActivity())




       // val priceProduct = arguments?.getFloat("product_price")


        //Adding values saved in viewmodel map in cartProductList so that all adapter can fetch values one by one from list
        for (i in viewModelForCart.mapIdQuantity) {
          //  cartProductList.add(CartProductDataModel(i.first,i.third,(i.first * i.second)))
           // cartProductList.add(CartProductDataModel(i.quantity, i.title,(i.price*i.quantity),i.image))
          //  cartProductsDbList.add(Entity(quantity =i.quantity,title =i.title,price =(i.price*i.quantity),image =i.image))
            itemDb.itemDao().insert(Entity(id = i.key,quantity =i.value.quantity,title =i.value.title,price =(i.value.price*i.value.quantity),image =i.value.image))



        }

      /* val view = layoutInflater.inflate(R.layout.cart_item_layout, null)
        val valueOfPrice = view.findViewById<TextView>(R.id.total_price_text)
        valueOfPrice.text = "$currency ${binding.cartProductDetail?.price}" */


      //  Log.d("cartArray", cartProductList.toString())
      //  binding.lifecycleOwner = viewLifecycleOwner
       // binding.cartRecyclerView.adapter = CartAdapter(cartProductList)

        items.addAll(itemDb.itemDao().getItems())
        binding.cartRecyclerView.adapter = CartAdapter(items)


        val itemUpdateListener = object: ClickListener{
            override fun onUpdate(cartItemProduct: CartProductDataModel) {


            }

            override fun onClick(itemProduct: ProductDataModel) {
                TODO("Not yet implemented")
            }
        }
        binding.btnContinueCheckout.setOnClickListener {
            var totalPriceOfItemsInCart = 0.0
           // for (i in 0..cartProductList.size-1) totalPriceOfItemsInCart+=cartProductList[i].price
            for (i in 0..items.size-1) totalPriceOfItemsInCart+=items[i].price

            val bundle = Bundle()
           // bundle.putInt("total_item_in_cart", cartProductList.size)
            bundle.putInt("total_item_in_cart", items.size)
            bundle.putDouble("total_price_of_item_in_cart", totalPriceOfItemsInCart)

            val fragmentPlaceOrder = PlaceOrderFragment()
            fragmentPlaceOrder.arguments = bundle // arguments attached with bundle
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.flFragment, fragmentPlaceOrder)
            fragmentTransaction?.addToBackStack("")
            fragmentTransaction?.commit()

        }



    }
    fun setUpRecyclerView() {

    }




    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(activity,"onDestroyCartCalled",Toast.LENGTH_SHORT).show()
    }



}