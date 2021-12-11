package com.example.voiceproj.recorder.ui.view.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.voiceproj.R
import com.example.voiceproj.databinding.FragmentHomeBinding
import com.example.voiceproj.databinding.FragmentRecordingBinding
import com.example.voiceproj.recorder.ui.viewmodel.RecorderViewModel
import com.example.voiceproj.recorder.ui.viewstate.RecorderState
import com.example.voiceproj.recorder.util.InjectorUtils
import com.example.voiceproj.ui.main.fragments.HomeFragment
import com.example.voiceproj.ui.main.view.MainActivity
import com.example.voiceproj.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class RecorderFragment : Fragment() {

    private lateinit var recorderViewModel: RecorderViewModel

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : RecorderFragment {
            return RecorderFragment()
        }
    }

    private lateinit var recordingListFragment: RecordingListFragment
    private lateinit var binding : FragmentRecordingBinding
    private lateinit var mainActivity : MainActivity

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "RecorderFragment : onCreate Called")
    }
    // 프래그먼트를 안고 잇는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
        Log.d(TAG, "RecorderFragment : onAttach Called")
    }
    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")
        return inflater.inflate(R.layout.fragment_recording, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecordingBinding.bind(view)
        recordingListFragment = RecordingListFragment.newInstance()

        initUI()

        binding.fabStartRecording.setOnClickListener {
            if (ContextCompat.checkSelfPermission(mainActivity,
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(mainActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(mainActivity, permissions,0)
            } else {
                startRecording()
            }

        }

        binding.fabStopRecording.setOnClickListener{
            stopRecording()
        }

        binding.fabPauseRecording.setOnClickListener {
            pauseRecording()
        }

        binding.fabResumeRecording.setOnClickListener {
            resumeRecording()
        }

        binding.fabRecordings.setOnClickListener {
            Toast.makeText(mainActivity, "녹음파일 리스트로 이동합니다.", Toast.LENGTH_SHORT).show()
            mainActivity.replaceFragment(recordingListFragment)

        }

        if(recorderViewModel?.recorderState == RecorderState.Stopped){
            binding.fabStopRecording.isEnabled = false
        }

    }



    private fun initUI() {
        //Get the viewmodel factory

        val factory = InjectorUtils.provideRecorderViewModelFactory(requireActivity().externalCacheDir.toString(), mainActivity.applicationContext.contentResolver)
//        val factory = InjectorUtils.provideRecorderViewModelFactory(mainActivity.getFilePath(), mainActivity.applicationContext.contentResolver)

        //Getting the viewmodel
        recorderViewModel = ViewModelProviders.of(this, factory).get(RecorderViewModel::class.java)

        addObserver()
    }

    private fun addObserver() {
        recorderViewModel?.getRecordingTime()?.observe(mainActivity, Observer {
            binding.textviewRecordingTime.text = it
        })
    }


    @SuppressLint("RestrictedApi")
    private fun startRecording() {
        recorderViewModel?.startRecording()

        binding.fabStopRecording.isEnabled = true
        binding.fabStartRecording.visibility = View.INVISIBLE
        binding.fabPauseRecording.visibility = View.VISIBLE
        binding.fabResumeRecording.visibility = View.INVISIBLE
    }

    @SuppressLint("RestrictedApi")
    private fun stopRecording(){
        recorderViewModel?.stopRecording()

        binding.fabStopRecording.isEnabled = false
        binding.fabStartRecording.visibility = View.VISIBLE
        binding.fabPauseRecording.visibility = View.INVISIBLE
        binding.fabResumeRecording.visibility = View.INVISIBLE

    }

    @TargetApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    private fun pauseRecording(){
        recorderViewModel?.pauseRecording()

        binding.fabStopRecording.isEnabled = true
        binding.fabStartRecording.visibility = View.INVISIBLE
        binding.fabPauseRecording.visibility = View.INVISIBLE
        binding.fabResumeRecording.visibility = View.VISIBLE

    }

    @TargetApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    private fun resumeRecording(){
        recorderViewModel?.resumeRecording()

        binding.fabStopRecording.isEnabled = true
        binding.fabStartRecording.visibility = View.INVISIBLE
        binding.fabPauseRecording.visibility = View.VISIBLE
        binding.fabResumeRecording.visibility = View.INVISIBLE
    }


    override fun onDestroyView() {
//        binding = null
        super.onDestroyView()
    }
}