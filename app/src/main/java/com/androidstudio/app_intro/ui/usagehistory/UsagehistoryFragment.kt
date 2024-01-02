package com.androidstudio.app_intro.ui.usagehistory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstudio.app_intro.R

class UsagehistoryFragment : Fragment() {

    companion object {
        fun newInstance() = UsagehistoryFragment()
    }

    private lateinit var viewModel: UsagehistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_usagehistory, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsagehistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}