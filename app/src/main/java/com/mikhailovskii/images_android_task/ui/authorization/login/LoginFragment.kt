package com.mikhailovskii.images_android_task.ui.authorization.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.databinding.FragmentLoginBinding
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment(), ViewBindingStrategy<FragmentLoginBinding>, AndroidScopeComponent {
    override val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    override val scope by fragmentScope()

    private val viewModel by viewModel<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etEmail.doAfterTextChanged {
            viewModel.setEmail(it.toString())
        }
        binding.etPassword.doAfterTextChanged {
            viewModel.setPassword(it.toString())
        }
        binding.btnLogin.setOnClickListener {
            viewModel.login()
        }
        binding.tvRegisterAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }
    }
}