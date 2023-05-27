package com.mikhailovskii.images_android_task.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return when (this) {
            is ViewBindingStrategy<*> -> binding.root
            else -> error("Fragment must implement ViewBindingStrategy")
        }
    }

}