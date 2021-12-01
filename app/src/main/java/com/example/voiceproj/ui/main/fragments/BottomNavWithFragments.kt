package com.example.voiceproj.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.voiceproj.R
import com.example.voiceproj.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class BottomNavWithFragments : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var chartFragment: ChartFragment
    private lateinit var communityFragment: CommunityFragment
    private lateinit var chatFragment: ChartFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var binding : ActivityMainBinding
    val TAG : String = HomeFragment.TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_home -> {
                Log.d(TAG, "onNavigationItemSelected: home")
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout, homeFragment).commit()
            }
            R.id.menu_chart -> {
                Log.d(TAG, "onNavigationItemSelected: chart")
                chartFragment = ChartFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout, chartFragment).commit()
            }
            R.id.menu_community -> {
                Log.d(TAG, "onNavigationItemSelected: community")
                communityFragment = CommunityFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout, communityFragment).commit()
            }
            R.id.menu_chat -> {
                Log.d(TAG, "onNavigationItemSelected: chat")
                chatFragment = ChartFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout, chatFragment).commit()
            }
            R.id.menu_profile -> {
                Log.d(TAG, "onNavigationItemSelected: profile")
                profileFragment = ProfileFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout, profileFragment).commit()
            }
        }

        return true
    }

}