package com.androidstudio.app_intro.ui.usagevalidation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstudio.app_intro.R

class UsagevalidationFragment : Fragment() {

    companion object {
        fun newInstance() = UsagevalidationFragment()
    }

    private lateinit var viewModel: UsagevalidationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_usagevalidation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsagevalidationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}