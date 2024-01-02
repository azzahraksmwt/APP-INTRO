package com.androidstudio.app_intro.ui.usageform

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstudio.app_intro.R

class UsageformFragment : Fragment() {

    companion object {
        fun newInstance() = UsageformFragment()
    }

    private lateinit var viewModel: UsageformViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_usageform, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsageformViewModel::class.java)
        // TODO: Use the ViewModel
    }

}