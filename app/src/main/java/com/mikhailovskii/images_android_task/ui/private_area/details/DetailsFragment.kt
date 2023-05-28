package com.mikhailovskii.images_android_task.ui.private_area.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mikhailovskii.images_android_task.R
import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment(), ViewBindingStrategy<FragmentDetailsBinding> {

    override val binding by lazy { FragmentDetailsBinding.inflate(layoutInflater) }

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val payload = args.payload ?: return

        binding.tvSize.text = getString(R.string.image_details_size, payload.size)
        binding.tvType.text = getString(R.string.image_details_type, payload.type)
        binding.tvTags.text = getString(R.string.image_details_tags, payload.tags)
        binding.tvUserName.text = getString(R.string.image_details_user_name, payload.userName)
        binding.tvViews.text = getString(R.string.image_details_views, payload.views)
        binding.tvLikes.text = getString(R.string.image_details_likes, payload.likes)
        binding.tvComments.text = getString(R.string.image_details_comments, payload.comments)
        binding.tvFavorites.text = getString(R.string.image_details_favorites, payload.favorites)
        binding.tvDownloads.text = getString(R.string.image_details_downloads, payload.downloads)

        Glide.with(requireContext())
            .load(payload.url)
            .into(binding.ivImage)
    }
}