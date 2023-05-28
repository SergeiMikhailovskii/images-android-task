package com.mikhailovskii.images_android_task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.images_android_task.databinding.ImageItemBinding

internal class ImagesHomeAdapter(
    private val onItemClick: (HomeImageInfo) -> Unit
) : RecyclerView.Adapter<ImagesHomeAdapter.ViewHolder>() {

    private val items = mutableListOf<HomeImageInfo>()

    inner class ViewHolder(
        private val binding: ImageItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: HomeImageInfo) {
            binding.tvUserName.text = data.userName
            Glide.with(binding.root.context)
                .load(data.url)
                .into(binding.ivImage)
            binding.root.setOnClickListener { onItemClick(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ImageItemBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    fun setData(items: List<HomeImageInfo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyItemRangeChanged(0, items.size)
    }
}