package com.example.ecomapp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.window.SplashScreen
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentTransaction
import com.example.ecomapp.view.HomeFragment
import com.example.ecomapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isLoading.value
            }

        }
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment()

        //Starting the fragment transaction from SupportFragmentManager class
        val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flFragment, homeFragment)
        ft.commit()
    }
}