package com.example.ecomapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomapp.adapter.MyOrderAdapter
import com.example.ecomapp.db.Entity
import com.example.ecomapp.db.EntityMyOrder
import com.example.ecomapp.db.ItemDao
import com.example.ecomapp.db.ItemRoomDatabase
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
        Toast.makeText(context,"MyOrderOncreateviewcalled",Toast.LENGTH_SHORT).show()

        val nameFromPlaceOrder = arguments?.getString("userName")
        val stateOfOrder = arguments?.getString("stateWhereOrderWillDeliver")
       // viewModelMyOrder.stateOfOrder = stateOfOrder.toString()
        val pinCodeOfOrder = arguments?.getString("pinCodeWhereOrderWillDeliver")
       // viewModelMyOrder.pinCodeOfOrder =pinCodeOfOrder.toString()
        val dateOfOrder = arguments?.getString("dateOfOrder")
       // viewModelMyOrder.dateOfOrder = dateOfOrder.toString()
        val paymentMode = arguments?.getString("paymentModeOfOrder")
       // viewModelMyOrder.paymentMode = paymentMode.toString()
        val view = inflater.inflate(R.layout.fragment_my_orders, container, false)
      //  val orderUserName : TextView = view.findViewById(R.id.myordername)
      //  orderUserName.text = nameFromPlaceOrder

        //val myOrderProductList = arrayListOf<MyOrderProductDataModel>()
        val myOrderProductList = arrayListOf<EntityMyOrder>()
        val itemDb: ItemRoomDatabase = ItemRoomDatabase.getDatabase(requireActivity())
      viewModelMyOrder.mapIdQuantityForMyOrder.putAll(viewModelMyOrder.mapIdQuantity)
       /* for (i in viewModelMyOrder.mapIdQuantityForMyOrder.values) {
            // We are not using the values that we received from bundle because value may destroy as the previous fragment will destroy
            // We are using the values of viewModels because we can get the preserved value in MyOrderScreen
          /*  myOrderProductList.add(MyOrderProductDataModel(i.first,i.third,
                otherDetails = OtherDetailsModel(viewModelMyOrder.dateOfOrder,
                    viewModelMyOrder.stateOfOrder, viewModelMyOrder.pinCodeOfOrder, viewModelMyOrder.paymentMode
                ) )) */
            myOrderProductList.add(MyOrderProductDataModel(i.quantity,i.title,
                otherDetails = OtherDetailsModel(viewModelMyOrder.dateOfOrder,
                    viewModelMyOrder.stateOfOrder, viewModelMyOrder.pinCodeOfOrder, viewModelMyOrder.paymentMode
                ) ))
        } */

        for (i in itemDb.itemDao().getItems()) {
            itemDb.itemDao().insertInMyOrder(EntityMyOrder(quantity = i.quantity, title = i.title, date = viewModelMyOrder.dateOfOrder))

        }



        Log.d("MyOrder", myOrderProductList.size.toString())
        Log.d("MyOrderDate", viewModelMyOrder.dateOfOrder)
      //  Log.d("MyOrderState", stateOfOrder.toString())
      //  Log.d("MyOrderPin", pinCodeOfOrder.toString())
        val rvMyOrder = view?.findViewById<RecyclerView>(R.id.myOrderRecyclerView)
       // rvMyOrder?.adapter = MyOrderAdapter(myOrderProductList)
        rvMyOrder?.adapter = MyOrderAdapter(itemDb.itemDao().getItemsOfMyOrder())
       // viewModelMyOrder.mapIdQuantity.clear()
        itemDb.itemDao().deleteCart(itemDb.itemDao().getItems())



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context,"MyOrderOnViewCreatedCalled", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(context,"MyOrderOnResumeCalled", Toast.LENGTH_SHORT).show()
        val nameFromPlaceOrder = arguments?.getString("userName")
        val stateOfOrder = arguments?.getString("stateWhereOrderWillDeliver")
        val pinCodeOfOrder = arguments?.getString("pinCodeWhereOrderWillDeliver")
        val dateOfOrder = arguments?.getString("dateOfOrder")
        val paymentMode = arguments?.getString("paymentModeOfOrder")


    }




}