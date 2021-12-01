package com.example.voiceproj.recorder.ui.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.voiceproj.R
import com.example.voiceproj.databinding.FragmentHomeBinding
import com.example.voiceproj.ui.main.view.MainActivity
import com.example.voiceproj.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class RecordingListFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : RecordingListFragment {
            return RecordingListFragment()
        }
    }

    private lateinit var binding : FragmentHomeBinding
    private lateinit var mainActivity : MainActivity

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        button.setOnClickListener(this)
        Log.d(TAG, "HomeFragment : onCreate Called")
    }
    // 프래그먼트를 안고 잇는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
        Log.d(TAG, "HomeFragment : onAttach Called")
    }
    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.btnShowSelPopAnal.setOnClickListener {

            // Dialog만들기
            val mDialogView = LayoutInflater.from(mainActivity).inflate(R.layout.sel_pop_anal, null)
            val mBuilder = AlertDialog.Builder(mainActivity)
                .setView(mDialogView)
                .setTitle("유형 선택")

            mBuilder.show()
        }

    }


    override fun onDestroyView() {
//        binding = null
        super.onDestroyView()
    }
}