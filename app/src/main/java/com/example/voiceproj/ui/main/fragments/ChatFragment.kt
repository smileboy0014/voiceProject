package com.example.voiceproj.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voiceproj.R
import com.example.voiceproj.databinding.FragmentChatBinding
import com.example.voiceproj.mvi.data.api.ApiHelperImpl
import com.example.voiceproj.mvi.data.api.RetrofitBuilder
import com.example.voiceproj.mvi.data.model.User
import com.example.voiceproj.ui.main.adapter.MainAdapter
import com.example.voiceproj.ui.main.intent.MainIntent
import com.example.voiceproj.ui.main.view.MainActivity
import com.example.voiceproj.ui.main.viewmodel.MainViewModel
import com.example.voiceproj.ui.main.viewstate.MainState
import com.example.voiceproj.util.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChatFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : ChatFragment {
            return ChatFragment()
        }
    }

    private lateinit var binding : FragmentChatBinding
    private lateinit var mainActivity : MainActivity

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "ChatFragment : onCreate Called")

    }
    // 프래그먼트를 안고 잇는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity

        Log.d(TAG, "ChatFragment : onAttach Called")
    }
    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")
//        binding = FragmentChatBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        setupUI()
        setupViewModel()
        observeViewModel()

        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainIntent.FetchUser)
        }
    }

    override fun onDestroyView() {
//        binding = null
        super.onDestroyView()
    }

    private fun setupUI() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    binding.recyclerView.context,
                    (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        binding.recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(MainViewModel::class.java)
    }

    private fun observeViewModel() {
//        progressBar = findViewById(R.id.progressBar)

        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        binding.progressBar.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(mainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        binding.recyclerView.visibility = View.VISIBLE

        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()

    }
}