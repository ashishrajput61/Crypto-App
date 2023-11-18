package com.ashish.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.navigation.NavHost
import androidx.navigation.fragment.findNavController
import com.ashish.cryptoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        val navController=navHostFragment!!.findNavController()

        val popupMenu=PopupMenu(this,null)
        popupMenu.inflate(R.menu.bottom_bar)

        binding.smoothBottombar.setupWithNavController(popupMenu.menu,navController)
    }
}