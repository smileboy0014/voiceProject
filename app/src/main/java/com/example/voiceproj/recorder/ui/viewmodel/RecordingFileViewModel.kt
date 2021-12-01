package com.example.voiceproj.recorder.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.voiceproj.recorder.data.repository.RecordingFileRepository

class RecordingFileViewModel(val recordingRepository: RecordingFileRepository): ViewModel(){

    fun getRecordings() = recordingRepository.getRecordings()
}