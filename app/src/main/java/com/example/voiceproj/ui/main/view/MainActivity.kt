package com.example.voiceproj.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.voiceproj.R
import com.example.voiceproj.databinding.ActivityMainBinding
import com.example.voiceproj.recorder.ui.view.fragments.RecorderFragment
import com.example.voiceproj.ui.main.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var homeFragment: HomeFragment
    private lateinit var chartFragment: ChartFragment
    private lateinit var communityFragment: CommunityFragment
    private lateinit var chatFragment: ChatFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var binding : ActivityMainBinding

    val TAG : String = HomeFragment.TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 하단 네비게이션 뷰를 바인딩하기(클릭시 이벤트 발생을 위해)
        binding.bottomNav.setOnNavigationItemSelectedListener(this)

        // 처음 앱 기동 시 homeFragment 화면 보이도록 강제로 띄우기
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, homeFragment).commit()
        }

    // 하단 네비게이션 아이콘 클릭할 때 마다 Fragment 교체해 주는 메서드
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
                chatFragment = ChatFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout, chatFragment).commit()

//                lifecycleScope.launch {
//                    mainViewModel.userIntent.send(MainIntent.FetchUser)
//                }
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

     fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, fragment).commit()

    }

}