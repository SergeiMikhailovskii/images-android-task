package com.mikhailovskii.images_android_task.ui.authorization.registration

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mikhailovskii.domain.failure.Failure
import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.databinding.FragmentRegistrationBinding
import com.mikhailovskii.images_android_task.extension.getString
import com.mikhailovskii.images_android_task.extension.observe
import com.mikhailovskii.images_android_task.route.Route
import kotlinx.coroutines.launch
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : BaseFragment(), ViewBindingStrategy<FragmentRegistrationBinding>, AndroidScopeComponent {
    override val binding by lazy { FragmentRegistrationBinding.inflate(layoutInflater) }
    override val scope by fragmentScope()

    private val viewModel by viewModel<RegistrationViewModel>()

    init {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                with(viewModel) {
                    observe(routeLiveData, ::handleRoute)
                    observe(failureLiveData, ::handleFailure)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etEmail.doAfterTextChanged {
            viewModel.setEmail(it.toString())
            binding.tvEmailError.isVisible = false
        }
        binding.etPassword.doAfterTextChanged {
            viewModel.setPassword(it.toString())
            binding.tvPasswordError.isVisible = false
        }
        binding.etAge.doAfterTextChanged {
            viewModel.setAge(it.toString())
            binding.tvAgeError.isVisible = false
        }
        binding.btnRegister.setOnClickListener {
            viewModel.register()
        }
    }

    private fun handleRoute(route: Route?) {
        if (route == Route.PrivateArea.Home) {
            println("* open home")
        }
    }

    private fun handleFailure(failure: Failure?) {
        if (failure is Failure.FieldsFailure) {
            failure.validationErrors.firstOrNull { it.subject == "password" }?.errorMessage?.let {
                binding.tvPasswordError.isVisible = true
                binding.tvPasswordError.text = requireContext().getString(it)
            }
            failure.validationErrors.firstOrNull { it.subject == "email" }?.errorMessage?.let {
                binding.tvEmailError.isVisible = true
                binding.tvEmailError.text = requireContext().getString(it)
            }
            failure.validationErrors.firstOrNull { it.subject == "age" }?.errorMessage?.let {
                binding.tvAgeError.isVisible = true
                binding.tvAgeError.text = requireContext().getString(it)
            }
        }
    }
}