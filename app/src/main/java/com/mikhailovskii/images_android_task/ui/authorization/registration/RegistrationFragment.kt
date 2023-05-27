package com.mikhailovskii.images_android_task.ui.authorization.registration

import com.mikhailovskii.images_android_task.base.BaseFragment
import com.mikhailovskii.images_android_task.base.ViewBindingStrategy
import com.mikhailovskii.images_android_task.databinding.FragmentRegistrationBinding

class RegistrationFragment : BaseFragment(), ViewBindingStrategy<FragmentRegistrationBinding> {
    override val binding by lazy {
        FragmentRegistrationBinding.inflate(layoutInflater)
    }

}