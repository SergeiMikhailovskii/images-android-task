package com.mikhailovskii.images_android_task.ui.private_area.home

import android.os.Bundle
import android.view.View
import com.mikhailovskii.images_android_task.adapter.ImagesHomeAdapter
import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.databinding.FragmentHomeBinding
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), ViewBindingStrategy<FragmentHomeBinding>, AndroidScopeComponent {

    override val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override val scope by fragmentScope()

    private val adapter by lazy(::ImagesHomeAdapter)
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadImages()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvImages.adapter = adapter
    }
}