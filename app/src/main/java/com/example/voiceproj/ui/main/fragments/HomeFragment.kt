package com.example.voiceproj.ui.main.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.voiceproj.R
import com.example.voiceproj.databinding.FragmentHomeBinding
import com.example.voiceproj.recorder.ui.view.fragments.RecorderFragment
import com.example.voiceproj.ui.main.view.MainActivity
import com.example.voiceproj.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }

    private lateinit var binding : FragmentHomeBinding
    private lateinit var recorderFragment : RecorderFragment
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
        recorderFragment = RecorderFragment.newInstance()

        binding.btnShowSelPopAnal.setOnClickListener {

            // Dialog만들기
            val mDialogView = LayoutInflater.from(mainActivity).inflate(R.layout.sel_pop_anal, null)
            val mBuilder = AlertDialog.Builder(mainActivity)
                .setView(mDialogView)
                .setTitle("유형 선택")

            val mSelDialog = mBuilder.show()

            val btnMoveLiveAnal = mDialogView.findViewById<Button>(R.id.btn_move_live_anl)
            btnMoveLiveAnal.setOnClickListener {

                Toast.makeText(mainActivity, "실시간 유형 분석으로 이동합니다.", Toast.LENGTH_SHORT).show()
                mainActivity.replaceFragment(recorderFragment)
                mSelDialog.dismiss()


            }

            val btnMoveLoadFile = mDialogView.findViewById<Button>(R.id.btn_move_load_file)
            btnMoveLoadFile.setOnClickListener {
                Toast.makeText(mainActivity, "기존 파일 분석으로 이동합니다.", Toast.LENGTH_SHORT).show()
                mSelDialog.dismiss()
            }

        }
    }



     override fun onDestroyView() {
//        binding = null
        super.onDestroyView()
    }
}