package com.example.voiceproj.recorder.util

import android.content.ContentResolver
import com.example.voiceproj.recorder.data.repository.RecorderRepository
import com.example.voiceproj.recorder.data.repository.RecordingFileRepository


object InjectorUtils {
    fun provideRecorderViewModelFactory(savePath: String, contentResolver: ContentResolver): RecorderViewModelProvider {
        val recorderRepository = RecorderRepository.getInstance(savePath, contentResolver)
        return RecorderViewModelProvider(recorderRepository)
    }

    fun provideRecordingViewModelFactory(): RecordingFileViewModelProvider {
        val recordingRepository = RecordingFileRepository.getInstance()
        return RecordingFileViewModelProvider(recordingRepository)
    }
}