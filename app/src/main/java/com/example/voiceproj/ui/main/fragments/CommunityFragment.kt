package com.example.voiceproj.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.voiceproj.databinding.FragmentComunityBinding

class CommunityFragment : Fragment() {

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : CommunityFragment {
            return CommunityFragment()
        }
    }

    private var binding : FragmentComunityBinding? = null

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "CommunityFragment : onCreate Called")
    }
    // 프래그먼트를 안고 잇는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "CommunityFragment : onAttach Called")
    }
    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")
        binding = FragmentComunityBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}