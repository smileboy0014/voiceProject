package com.example.voiceproj.recorder.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.voiceproj.recorder.data.repository.RecorderRepository
import com.example.voiceproj.recorder.ui.viewstate.RecorderState


class RecorderViewModel(private val recorderRepository: RecorderRepository): ViewModel() {

    var recorderState: RecorderState = RecorderState.Stopped

    fun startRecording() = recorderRepository.startRecording()

    fun stopRecording() = recorderRepository.stopRecording()

    fun pauseRecording() = recorderRepository.pauseRecording()

    fun resumeRecording() = recorderRepository.resumeRecording()

    fun getRecordingTime() = recorderRepository.getRecordingTime()

}