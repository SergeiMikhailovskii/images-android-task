package com.mikhailovskii.images_android_task.ui.private_area.details

import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment(), ViewBindingStrategy<FragmentDetailsBinding> {

    override val binding by lazy { FragmentDetailsBinding.inflate(layoutInflater) }

}