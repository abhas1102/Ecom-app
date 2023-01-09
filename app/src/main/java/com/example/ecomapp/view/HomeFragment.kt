package com.example.ecomapp.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.ecomapp.R
import com.example.ecomapp.adapter.ProductListAdapter
import com.example.ecomapp.clicklistener.ClickListener
import com.example.ecomapp.databinding.FragmentHomeBinding
import com.example.ecomapp.model.CartProductDataModel
import com.example.ecomapp.model.ProductDataModel
import com.example.ecomapp.viewmodel.HomeFragmentViewModel
import com.example.ecomapp.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()



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
        binding.lifecycleOwner = viewLifecycleOwner

        val clickListener = object: ClickListener{
            override fun onClick(itemProduct: ProductDataModel) {
                val productDetailFragment = ProductDetailFragment().apply {
                    arguments = bundleOf(Pair("product",itemProduct))
                    viewModel.quantityValue = 0
                }
                val ft : FragmentTransaction = fragmentManager!!.beginTransaction()
                ft.replace(R.id.flFragment, productDetailFragment,"PRODUCT_DETAIL_FRAGMENT")
                ft.addToBackStack(null)
                ft.commit()
            }

            override fun onUpdate(cartItemProduct: CartProductDataModel) {
                TODO("Not yet implemented")
            }

        }

        viewModel.products.observe(viewLifecycleOwner){
           // var adapter = ProductListAdapter(it,clickListener)
            var matchedItem : ArrayList<ProductDataModel> = arrayListOf()
            if (it.size>0){
                Log.d("HomeFragment",it.get(0).title)
               // Log.d("HomeFragment", it.get(0).image)
                binding.productRecyclerView.adapter = ProductListAdapter(it,clickListener)


            }

            binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                  //  var listOfTitles = arrayListOf<String>()
                 /*   binding.offerCard.visibility = View.GONE
                    binding.icCloth.visibility = View.GONE
                    binding.icElectronics.visibility = View.GONE
                    binding.icFurniture.visibility = View.GONE
                    binding.icShoes.visibility = View.GONE
                    binding.icJewellary.visibility = View.GONE
                    binding.icOthers.visibility = View.GONE */

                    binding.apply {
                        offerCard.isVisible = false
                        icCloth.isVisible = false
                        icElectronics.isVisible = false
                        icFurniture.isVisible = false
                        icShoes.isVisible = false
                        icJewellary.isVisible = false
                        icOthers.isVisible = false
                    }
                    it.forEach { item ->
                        if (item.title.contains(query!!)) {
                            matchedItem.add(item)
                        }
                    }
                    if (matchedItem.isEmpty()) {
                        Toast.makeText(activity,"No match found",Toast.LENGTH_SHORT).show()
                    } else{
                        binding.productRecyclerView.adapter = ProductListAdapter(matchedItem,clickListener)
                    }

                  //  for (i in 0..it.size-1) listOfTitles.add(it[i].title)
                   // Log.d("ListTitles", listOfTitles.toString())
                   /* for (i in it) {
                        if (i.title.contains(query!!)) {
                            newIt.add(i)
                            binding.productRecyclerView.adapter = ProductListAdapter(newIt,clickListener)
                        } else {
                            Toast.makeText(activity,"no search found", Toast.LENGTH_SHORT).show()
                        }
                    } */

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return  false
                }

            })
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

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(activity,"onDestroyHomeFrag",Toast.LENGTH_SHORT).show()
    }


}