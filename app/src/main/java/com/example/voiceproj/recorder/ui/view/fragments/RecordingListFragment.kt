package com.example.voiceproj.recorder.ui.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voiceproj.R
import com.example.voiceproj.databinding.FragmentHomeBinding
import com.example.voiceproj.databinding.FragmentRecordingBinding
import com.example.voiceproj.databinding.FragmentRecordingListBinding
import com.example.voiceproj.recorder.ui.adapter.RecordingFileAdapter
import com.example.voiceproj.recorder.ui.viewmodel.RecordingFileViewModel
import com.example.voiceproj.recorder.util.InjectorUtils
import com.example.voiceproj.ui.main.view.MainActivity
import com.example.voiceproj.ui.main.viewmodel.MainViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_recording_list.*
import kotlinx.coroutines.launch

class RecordingListFragment : Fragment() {

    private lateinit var recordingFileViewModel: RecordingFileViewModel

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : RecordingListFragment {
            return RecordingListFragment()
        }
    }

    private lateinit var recorderFragment: RecorderFragment
    private lateinit var binding : FragmentRecordingListBinding
    private lateinit var mainActivity : MainActivity
    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private var data: ArrayList<String>? = ArrayList<String>()


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

        return inflater.inflate(R.layout.fragment_recording_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecordingListBinding.bind(view)

        mainActivity.setSupportActionBarFun(binding.listToolbar)

        groupAdapter = GroupAdapter<ViewHolder>()

        initUI()
    }

    override fun onStart() {
        super.onStart()

        //Ably the span count and the adapter to the recyclerview
        binding.recordingsRecyclerview.apply {
            binding.recordingsRecyclerview.layoutManager = LinearLayoutManager(this.context)
            adapter = groupAdapter
        }
    }

    private fun initUI() {
        //Get the viewmodel factory
        val factory = InjectorUtils.provideRecordingViewModelFactory(requireActivity().externalCacheDir.toString())

        //Getting the viewmodel
        recordingFileViewModel = ViewModelProviders.of(mainActivity, factory).get(RecordingFileViewModel::class.java)

        updateAdapter()
    }

    private fun updateAdapter() {
        data = recordingFileViewModel?.getRecordings()
        println("Updating Adapter")
        groupAdapter.clear()

        if(data != null) {
            data!!.forEach {
                println("Data: $it")
                groupAdapter.add(RecordingFileAdapter(it,mainActivity))
            }
        }
    }




    override fun onDestroyView() {
//        binding = null
        super.onDestroyView()
    }
}