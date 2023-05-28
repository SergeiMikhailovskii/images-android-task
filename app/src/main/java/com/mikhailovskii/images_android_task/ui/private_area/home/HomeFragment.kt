package com.mikhailovskii.images_android_task.ui.private_area.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.images_android_task.adapter.ImagesHomeAdapter
import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.databinding.FragmentHomeBinding
import com.mikhailovskii.images_android_task.extension.observe
import com.mikhailovskii.images_android_task.route.Route
import kotlinx.coroutines.launch
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), ViewBindingStrategy<FragmentHomeBinding>, AndroidScopeComponent {

    override val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override val scope by fragmentScope()

    private val adapter by lazy {
        ImagesHomeAdapter {
            viewModel.openImageDetails(it)
        }
    }
    private val viewModel by viewModel<HomeViewModel>()

    init {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                with(viewModel) {
                    observe(routeLiveData, ::handleRoute)
                    observe(imagesLiveData, ::handleImages)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadImages()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvImages.adapter = adapter
    }

    private fun handleRoute(route: Route?) {
        if (route is Route.PrivateArea.Details) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
        }
    }

    private fun handleImages(images: List<HomeImageInfo>?) {
        images?.let(adapter::setData)
    }
}