package com.example.voiceproj.recorder.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.voiceproj.recorder.data.repository.RecordingFileRepository
import com.example.voiceproj.recorder.ui.viewmodel.RecordingFileViewModel

class RecordingFileViewModelProvider(val recordingFileRepository: RecordingFileRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecordingFileViewModel(recordingFileRepository) as T
    }
}