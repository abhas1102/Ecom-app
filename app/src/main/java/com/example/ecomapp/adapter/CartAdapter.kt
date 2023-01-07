package com.example.ecomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomapp.databinding.CartItemLayoutBinding
import com.example.ecomapp.model.CartProductDataModel
import com.example.ecomapp.model.ProductDataModel

class CartAdapter(val cartProductList:List<CartProductDataModel>):RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    class ViewHolder(val binding:CartItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CartProductDataModel) {
            binding.cartProductDetails = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data = cartProductList[position])
      //  holder.binding.totalPriceText.text = String.format("%.2f", cartProductList[position].price)
       // holder.binding.totalPriceText.text = cartProductList[position].price.toString()

    }

    override fun getItemCount(): Int {
        return cartProductList.size
    }


}