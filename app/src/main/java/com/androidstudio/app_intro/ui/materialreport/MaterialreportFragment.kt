package com.androidstudio.app_intro.ui.materialreport

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstudio.app_intro.R

class MaterialreportFragment : Fragment() {

    companion object {
        fun newInstance() = MaterialreportFragment()
    }

    private lateinit var viewModel: MaterialreportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_materialreport, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MaterialreportViewModel::class.java)
        // TODO: Use the ViewModel
    }

}