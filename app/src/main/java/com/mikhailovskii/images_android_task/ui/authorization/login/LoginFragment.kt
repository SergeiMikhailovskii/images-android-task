package com.mikhailovskii.images_android_task.ui.authorization.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mikhailovskii.domain.failure.Failure
import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.component.PrivateAreaActivity
import com.mikhailovskii.images_android_task.databinding.FragmentLoginBinding
import com.mikhailovskii.images_android_task.extension.getString
import com.mikhailovskii.images_android_task.extension.observe
import com.mikhailovskii.images_android_task.route.Route
import kotlinx.coroutines.launch
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment(), ViewBindingStrategy<FragmentLoginBinding>, AndroidScopeComponent {
    override val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    override val scope by fragmentScope()

    private val viewModel by viewModel<LoginViewModel>()

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
        binding.btnLogin.setOnClickListener {
            viewModel.login()
        }
        binding.tvRegisterAccount.setOnClickListener {
            viewModel.openRegistration()
        }
    }

    private fun handleRoute(route: Route?) {
        if (route == Route.Authorization.Registration) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        } else if (route == Route.PrivateArea.Home) {
            startActivity(Intent(requireContext(), PrivateAreaActivity::class.java))
            requireActivity().finish()
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
        }
    }
}
