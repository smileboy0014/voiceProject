package com.example.voiceproj.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.voiceproj.R
import com.example.voiceproj.databinding.ListItemCompoBinding
import com.example.voiceproj.mvi.data.model.User
import kotlinx.android.synthetic.main.list_item_compo.view.*


class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ListItemCompoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {

            binding.textViewUserName.text = user.name
            binding.textViewUserEmail.text = user.email
            Glide.with(binding.imageViewAvatar.context)
                .load(user.avatar)
                .into(binding.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder{

        val binding = ListItemCompoBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return DataViewHolder(binding)
    }


    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<User>) {
        users.addAll(list)
    }

}