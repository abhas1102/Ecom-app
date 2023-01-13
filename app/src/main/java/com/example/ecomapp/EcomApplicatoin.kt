package com.example.ecomapp

import android.app.Application
import com.example.ecomapp.db.ItemRoomDatabase

class EcomApplicatoin: Application() {
    override fun onCreate() {
        super.onCreate()
    }
fun getItemDb() :ItemRoomDatabase{
return ItemRoomDatabase.getDatabase(this)
}
}