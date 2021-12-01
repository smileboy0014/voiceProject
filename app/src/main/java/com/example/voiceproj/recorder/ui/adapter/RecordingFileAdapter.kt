package com.example.voiceproj.recorder.ui.adapter

import android.content.Context
import com.example.voiceproj.R
import com.example.voiceproj.recorder.data.repository.RecordingFileRepository
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.list_item_compo_recording.view.*

class RecordingFileAdapter(private val title: String, val context: Context): Item(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.recording_title_textview.text = title
        viewHolder.itemView.recording_image.setOnClickListener {
            RecordingFileRepository.playRecording(context, title )
        }
    }

    override fun getLayout(): Int {
        return R.layout.list_item_compo_recording
    }

}