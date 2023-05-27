package com.mikhailovskii.images_android_task.ui.authorization

import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment(), ViewBindingStrategy<FragmentLoginBinding> {
    override val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
}