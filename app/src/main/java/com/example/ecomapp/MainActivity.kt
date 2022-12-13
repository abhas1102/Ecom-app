package com.example.ecomapp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.window.SplashScreen
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ecomapp.view.HomeFragment
import com.example.ecomapp.view.ProductDetailFragment
import com.example.ecomapp.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenu

class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModels()
    var productDetailFragment = ProductDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isLoading.value
            }

        }
        setContentView(R.layout.activity_main)
        fun replaceFragment(fragment: Fragment) {
            val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.flFragment, fragment,"HOME_FRAGMENT")
            ft.addToBackStack(null)
            ft.commit()
        }
        replaceFragment(HomeFragment())
        val navigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        navigation.setOnItemReselectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.cart -> replaceFragment(CartFragment())
            }
        }
       // val homeFragment = HomeFragment()

        //Starting the fragment transaction from SupportFragmentManager class




       /* val productDetailFragment:ProductDetailFragment =  supportFragmentManager?.findFragmentByTag("PRODUCT_DETAIL_FRAGMENT") as ProductDetailFragment
        if (productDetailFragment!=null && productDetailFragment.isVisible){
            var navBar:BottomNavigationView = findViewById(R.id.bottomNavigation)
            navBar.visibility = View.GONE
        } */


    }

   /* override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        supportFragmentManager.putFragment(outState,"productDetailFragment", productDetailFragment)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        productDetailFragment = supportFragmentManager.getFragment(savedInstanceState,"productDetailFragment") as ProductDetailFragment
    } */
}