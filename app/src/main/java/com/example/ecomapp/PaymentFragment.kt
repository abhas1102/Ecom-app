package com.example.ecomapp

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast


class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_payment, container, false)
        val radioGroup:RadioGroup = view.findViewById(R.id.radiogroup)
        val buttonPayment:Button = view.findViewById(R.id.btnContinuePayment)


        buttonPayment.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            val radioButton:RadioButton = view.findViewById(selectedId)

            val modeOfPayment:String = radioButton.text.toString()
            Toast.makeText(context,"Mode of Payment is $modeOfPayment", Toast.LENGTH_SHORT).show()
            val progress = ProgressDialog(context)
            progress.setMessage("Please wait while we are confirming your order")

        }

        return view
    }





}