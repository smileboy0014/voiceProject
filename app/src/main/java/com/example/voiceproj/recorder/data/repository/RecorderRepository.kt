package com.example.voiceproj.recorder.data.repository

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.ContentResolver
import android.content.ContentValues
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import java.io.IOException
import java.io.File
import java.util.*




class RecorderRepository(savePath : String, contentResolver: ContentResolver){


    companion object {
        @Volatile
        private var instance: RecorderRepository? = null

        fun getInstance(savePath: String, contentResolver: ContentResolver) =
            instance ?: synchronized(this) {
                instance ?: RecorderRepository(savePath, contentResolver).also { instance = it }
            }
    }

    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
//    private val dir: File = File(Environment.getExternalStorageDirectory().absolutePath + "/soundrecorder/")
    private val dir: File = File("$savePath/recorder/")


    private var recordingTime: Long = 0
    private var timer = Timer()
    private val recordingTimeString = MutableLiveData<String>()


    init {
        try{
            // create a File object for the parent directory
            val recorderDirectory = File("$savePath/recorder/")
            // have the object build the directory structure, if needed.
            recorderDirectory.mkdirs()
        }catch (e: IOException){
            e.printStackTrace()
        }

        if(dir.exists() && dir.listFiles() != null){
            val count = dir.listFiles().size
            output = "$savePath/recorder/recording$count.mp3"
        }
//        else {
//            output = Environment.DIRECTORY_MUSIC + "/recorder/recording0.mp3"
//        }

//        mediaRecorder = MediaRecorder().apply {
//
//            setAudioSource(MediaRecorder.AudioSource.MIC)
//            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
//            setOutputFile(output)
//            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
//        }

        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)
    }

    @SuppressLint("RestrictedApi")
    fun startRecording() {
        try {
            println("Starting recording!")
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            startTimer()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @SuppressLint("RestrictedApi")
    fun stopRecording(){
        mediaRecorder?.stop()
        mediaRecorder?.release()
        stopTimer()
        resetTimer()

//        // Add a media item that other apps shouldn't see until the item is
//// fully written to the media store.
//        val resolver = contentResolver
//
//        // Find all audio files on the primary external storage device.
//// On API <= 28, use VOLUME_EXTERNAL instead.
//        val audioCollection = MediaStore.Audio.Media
//            .getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//
//        val songDetails = ContentValues().apply {
//            put(
//                MediaStore.Audio.Media.DISPLAY_NAME,
//                "My Workout Playlist.mp3")
//            put(MediaStore.Audio.Media.IS_PENDING, 1)
//        }
//
//        val songContentUri = resolver.insert(audioCollection, songDetails)
//
//        resolver.openFileDescriptor(songContentUri, "w", null).use { pfd ->
//            // Write data into the pending audio file.
//        }
//
//// Now that we're finished, release the "pending" status, and allow other apps to play the audio track.
//        songDetails.clear()
//        songDetails.put(MediaStore.Audio.Media.IS_PENDING, 0)
//        resolver.update(songContentUri, songDetails, null, null)

        initRecorder()
    }


    @TargetApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    fun pauseRecording(){
        stopTimer()
        mediaRecorder?.pause()
    }

    @TargetApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    fun resumeRecording(){
        timer = Timer()
        startTimer()
        mediaRecorder?.resume()
    }

    private fun initRecorder() {
        mediaRecorder = MediaRecorder()

        if(dir.exists()){
            val count = dir.listFiles().size
            output = Environment.getExternalStorageDirectory().absolutePath + "/soundrecorder/recording"+count+".mp3"
        }

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)
    }

    private fun startTimer(){
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                recordingTime += 1
                updateDisplay()
            }
        }, 1000, 1000)
    }

    private fun stopTimer(){
        timer.cancel()
    }


    private fun resetTimer() {
        timer.cancel()
        recordingTime = 0
        recordingTimeString.postValue("00:00")
    }

    private fun updateDisplay(){
        val minutes = recordingTime / (60)
        val seconds = recordingTime % 60
        val str = String.format("%d:%02d", minutes, seconds)
        recordingTimeString.postValue(str)
    }

    fun getRecordingTime() = recordingTimeString
}