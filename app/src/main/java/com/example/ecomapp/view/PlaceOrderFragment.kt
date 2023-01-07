package com.example.ecomapp.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ecomapp.MyOrdersFragment
import com.example.ecomapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class PlaceOrderFragment : Fragment(), AdapterView.OnItemClickListener {
    //val autoCompleteText:AutoCompleteTextView = view?.findViewById(R.id.autocomplete)
    lateinit var autoCompleteText:AutoCompleteTextView
    lateinit var textState:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_place_order, container, false)
        val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
        val currency:Currency = Currency.getInstance(Locale.getDefault())
        val symbol = currency.symbol

        val priceOfProducts = arguments?.getDouble("total_price_of_item_in_cart")
        val countOfProducts = arguments?.getInt("total_item_in_cart")

        val valueOfCountOfItems:TextView = view.findViewById(R.id.value_total_item_ordered)
        valueOfCountOfItems.text = countOfProducts.toString()

        val valueOfTotalPrice:TextView = view.findViewById(R.id.value_total_price)
      //  valueOfTotalPrice.text = priceOfProducts.toString()
       // valueOfTotalPrice.text = "$symbol ${priceOfProducts.toString()}"
        valueOfTotalPrice.text = "$symbol ${df.format(priceOfProducts)}"


        val taxAmountOnProducts = priceOfProducts!!*18/100

        val valueOfTaxAmount :TextView = view.findViewById(R.id.value_tax_amount)
        valueOfTaxAmount.text = "$symbol ${df.format(taxAmountOnProducts)}"

        val valueOfTotalAmountToPay: TextView = view.findViewById(R.id.value_total_amount_to_pay)
        valueOfTotalAmountToPay.text = "$symbol ${df.format(priceOfProducts + taxAmountOnProducts)} "

        autoCompleteText = view.findViewById(R.id.autocomplete)
        val listOfStates = listOf("Bihar", "Bihar", "Bihar", "Bihar")
        val adapter = ArrayAdapter(view.context, R.layout.list_states, listOfStates)
        autoCompleteText.setAdapter(adapter)

        autoCompleteText.setOnItemClickListener(this)


        val textName: TextInputLayout =view.findViewById(R.id.address_input_name)
        val stateName: TextInputLayout = view.findViewById(R.id.address_input_state)
        val pinCode: TextInputLayout = view.findViewById(R.id.address_input_pin_code)

        val button:Button = view.findViewById(R.id.btnConfirmOrder)
        button.setOnClickListener {
            try {
                val date = Calendar.getInstance().time
                val formatter = SimpleDateFormat.getDateInstance()
                val formattedDate = formatter.format(date)

               // val stateFromWhereOrderIsDone = stateName.

                val nameOfUser = textName.editText!!.text as Editable
                val pinCodeOfOrderDelivery = pinCode.editText!!.text as Editable
                Log.d("PlaceOrder", nameOfUser.toString().trim())
                Log.d("PlaceOrder", textState)
                val bundle = Bundle()
                bundle.putString("userName", nameOfUser.toString())
                bundle.putString("dateOfOrder", formattedDate.toString())
                bundle.putString("stateWhereOrderWillDeliver", textState)
                bundle.putString("pinCodeWhereOrderWillDeliver", pinCodeOfOrderDelivery.toString())
                bundle.putString("dateOfOrder",formattedDate)

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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        textState = item

    }


}