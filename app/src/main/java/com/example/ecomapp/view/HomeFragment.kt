package com.example.ecomapp.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.ecomapp.R
import com.example.ecomapp.adapter.ProductListAdapter
import com.example.ecomapp.clicklistener.ClickListener
import com.example.ecomapp.databinding.FragmentHomeBinding
import com.example.ecomapp.model.ProductDataModel
import com.example.ecomapp.viewmodel.HomeFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by lazy {
        ViewModelProvider(this).get(HomeFragmentViewModel::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val clickListener = object: ClickListener{
            override fun onClick(itemProduct: ProductDataModel) {
                val productDetailFragment = ProductDetailFragment()
                val ft : FragmentTransaction = fragmentManager!!.beginTransaction()
                ft.replace(R.id.flFragment, productDetailFragment,"PRODUCT_DETAIL_FRAGMENT")
                ft.addToBackStack(null)
                ft.commit()


            }

        }

        viewModel.products.observe(viewLifecycleOwner){
            if (it.size>0){
                //Log.d("HomeFragment",it.get(0).title)
                //Log.d("HomeFragment", it.get(0).image)
                binding.productRecyclerView.adapter = ProductListAdapter(it,clickListener)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}