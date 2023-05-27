package com.mikhailovskii.images_android_task.ui.authorization.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment(), ViewBindingStrategy<FragmentLoginBinding> {
    override val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRegisterAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }
    }

}