package com.example.voiceproj.recorder.ui.viewstate


sealed class RecorderState {

    object Stopped : RecorderState()
    object Running : RecorderState()
    object Paused : RecorderState()
}