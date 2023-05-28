package com.mikhailovskii.images_android_task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikhailovskii.images_android_task.databinding.ImageItemBinding

class ImagesHomeAdapter : RecyclerView.Adapter<ImagesHomeAdapter.ViewHolder>() {

    private val items = mutableListOf<Model>()

    class ViewHolder(
        private val binding: ImageItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: Model) {
            binding.tvUserName.text = data.userName
            Glide.with(binding.root.context)
                .load(data.url)
                .into(binding.ivImage)
        }
    }

    class Model(
        val url: String,
        val userName: String
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ImageItemBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }
}