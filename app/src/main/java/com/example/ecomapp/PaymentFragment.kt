package com.example.ecomapp

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.ecomapp.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PaymentFragment : Fragment() {
     private val viewModelPayment : MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //Now we don't need to receive these values because we saved the value from placeorderfragment to directly in viewodel variables
        val userName = arguments?.getString("userName")
        val dateOfOrder = arguments?.getString("dateOfOrder")
        val stateWhereOrderWillDeliver = arguments?.getString("stateWhereOrderWillDeliver")
        val pinCodeWhereOrderWillDeliver = arguments?.getString("pinCodeWhereOrderWillDeliver")


        val view = inflater.inflate(R.layout.fragment_payment, container, false)
        val textMethodPayment:TextView = view.findViewById(R.id.text_select_method_of_payment)
        val radioGroup:RadioGroup = view.findViewById(R.id.radiogroup)
        val buttonPayment:Button = view.findViewById(R.id.btnContinuePayment)
        val doneLinerLayout:LinearLayout =view.findViewById(R.id.llDone)
        doneLinerLayout.visibility = View.GONE


        buttonPayment.setOnClickListener {

            val selectedId = radioGroup.checkedRadioButtonId
            val radioButton:RadioButton = view.findViewById(selectedId)

            val modeOfPayment:String = radioButton.text.toString()
            viewModelPayment.paymentMode = modeOfPayment

            Toast.makeText(context,"Mode of Payment is $modeOfPayment", Toast.LENGTH_SHORT).show()
            textMethodPayment.visibility = View.GONE
            radioGroup.visibility = View.GONE
            buttonPayment.visibility = View.GONE
            doneLinerLayout.visibility = View.VISIBLE

            val bundle = Bundle()
            bundle.putString("userName", userName)
            bundle.putString("dateOfOrder", dateOfOrder)
            bundle.putString("stateWhereOrderWillDeliver", stateWhereOrderWillDeliver)
            bundle.putString("pinCodeWhereOrderWillDeliver", pinCodeWhereOrderWillDeliver)
            bundle.putString("paymentModeOfOrder", modeOfPayment)

            lifecycleScope.launch {
                delay(3000)
                navigateToFragment(bundle)

            }

        }

        return view
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