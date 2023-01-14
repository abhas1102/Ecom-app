package com.example.ecomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomapp.databinding.MyorderItemLayoutBinding
import com.example.ecomapp.db.Entity
import com.example.ecomapp.db.EntityMyOrder
import com.example.ecomapp.model.MyOrderProductDataModel

class MyOrderAdapter(val myOrderProductList:List<EntityMyOrder>):RecyclerView.Adapter<MyOrderAdapter.ViewHolder>() {

    class ViewHolder(val binding: MyorderItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(data: EntityMyOrder) {
            binding.myOrdersProductDetail = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MyorderItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data = myOrderProductList[position])
        // holder.binding.totalPriceText.text = cartProductList[position].price.toString()

    }

    override fun getItemCount(): Int {
        return myOrderProductList.size
    }
}