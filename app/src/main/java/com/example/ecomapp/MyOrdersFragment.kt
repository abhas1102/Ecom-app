package com.example.ecomapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomapp.adapter.MyOrderAdapter
import com.example.ecomapp.model.MyOrderProductDataModel
import com.example.ecomapp.model.OtherDetailsModel
import com.example.ecomapp.viewmodel.MainViewModel


class MyOrdersFragment : Fragment() {
   private val viewModelMyOrder : MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val nameFromPlaceOrder = arguments?.getString("userName")
        val stateOfOrder = arguments?.getString("stateWhereOrderWillDeliver")
        val pinCodeOfOrder = arguments?.getString("pinCodeWhereOrderWillDeliver")
        val dateOfOrder = arguments?.getString("dateOfOrder")
        val paymentMode = arguments?.getString("paymentModeOfOrder")
        val view = inflater.inflate(R.layout.fragment_my_orders, container, false)
      //  val orderUserName : TextView = view.findViewById(R.id.myordername)
      //  orderUserName.text = nameFromPlaceOrder

        val myOrderProductList = arrayListOf<MyOrderProductDataModel>()
        for (i in viewModelMyOrder.mapIdQuantity.values) {
            myOrderProductList.add(MyOrderProductDataModel(i.first,i.third,
                otherDetails = OtherDetailsModel(dateOfOrder,
                    stateOfOrder.toString(), pinCodeOfOrder.toString(), paymentMode.toString()
                ) ))
        }
        Log.d("MyOrder", myOrderProductList.size.toString())
        Log.d("MyOrder", dateOfOrder.toString())
        Log.d("MyOrder", stateOfOrder.toString())
        Log.d("MyOrder", pinCodeOfOrder.toString())
        val rvMyOrder = view?.findViewById<RecyclerView>(R.id.myOrderRecyclerView)
        rvMyOrder?.adapter = MyOrderAdapter(myOrderProductList)
        viewModelMyOrder.mapIdQuantity.clear()


        return view
    }
    private fun setUpRecyclerView() {

    }




}