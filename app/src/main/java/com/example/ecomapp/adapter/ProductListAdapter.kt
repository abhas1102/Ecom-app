package com.example.ecomapp.adapter

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomapp.R
import com.example.ecomapp.clicklistener.ClickListener
import com.example.ecomapp.databinding.ItemLayoutBinding
import com.example.ecomapp.model.ProductDataModel
import com.google.android.material.imageview.ShapeableImageView

class ProductListAdapter(val productList: ArrayList<ProductDataModel>, val clickListener:ClickListener):RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {


        fun bind(data: ProductDataModel) {
            binding.productDetails= data

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data = productList[position])


        holder.binding.productImage.setOnClickListener {
            Log.d("click listener",productList.get(position).title)
            clickListener.onClick(itemProduct = productList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }




}