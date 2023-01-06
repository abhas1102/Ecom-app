package com.example.ecomapp.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import com.example.ecomapp.MyOrdersFragment
import com.example.ecomapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception

class PlaceOrderFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_place_order, container, false)

        val priceOfProducts = arguments?.getDouble("total_price_of_item_in_cart")
        val countOfProducts = arguments?.getInt("total_item_in_cart")

        val valueOfCountOfItems:TextView = view.findViewById(R.id.value_total_item_ordered)
        valueOfCountOfItems.text = countOfProducts.toString()

        val valueOfTotalPrice:TextView = view.findViewById(R.id.value_total_price)
        valueOfTotalPrice.text = priceOfProducts.toString()

        val taxAmountOnProducts = priceOfProducts!!*18/100

        val valueOfTaxAmount :TextView = view.findViewById(R.id.value_tax_amount)
        valueOfTaxAmount.text = taxAmountOnProducts.toString()

        val valueOfTotalAmountToPay: TextView = view.findViewById(R.id.value_total_amount_to_pay)
        valueOfTotalAmountToPay.text = (priceOfProducts + taxAmountOnProducts).toString()

        val autoCompleteText:AutoCompleteTextView = view.findViewById(R.id.autocomplete)
        val listOfStates = listOf("Bihar", "Bihar", "Bihar", "Bihar")
        val adapter = ArrayAdapter(view.context, R.layout.list_states, listOfStates)
        autoCompleteText.setAdapter(adapter)

        val textName: TextInputLayout =view.findViewById(R.id.address_input_name)

        val button:Button = view.findViewById(R.id.btnConfirmOrder)
        button.setOnClickListener {
            try {

                val nameOfUser = textName.editText!!.text as Editable
                Log.d("PlaceOrder", nameOfUser.toString().trim())
                val bundle = Bundle()
                bundle.putString("userName", nameOfUser.toString())
                navigateToFragment(bundle)
            } catch (e:Exception){}
        }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
    }
    fun navigateToFragment(bundle: Bundle) {
        val myOrdersFragment = MyOrdersFragment()
        myOrdersFragment.arguments = bundle
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.flFragment,myOrdersFragment)
        fragmentTransaction?.addToBackStack("")
        fragmentTransaction?.commit()
    }


}