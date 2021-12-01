package com.example.voiceproj.recorder.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.voiceproj.recorder.data.repository.RecorderRepository
import com.example.voiceproj.recorder.ui.viewmodel.RecorderViewModel

class RecorderViewModelProvider(val recorderRepository: RecorderRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecorderViewModel(recorderRepository) as T
    }
}