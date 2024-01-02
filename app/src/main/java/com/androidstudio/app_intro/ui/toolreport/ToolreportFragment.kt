package com.androidstudio.app_intro.ui.toolreport

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstudio.app_intro.R

class ToolreportFragment : Fragment() {

    companion object {
        fun newInstance() = ToolreportFragment()
    }

    private lateinit var viewModel: ToolreportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toolreport, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ToolreportViewModel::class.java)
        // TODO: Use the ViewModel
    }

}