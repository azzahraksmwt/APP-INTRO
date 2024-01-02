package com.androidstudio.app_intro.ui.studentdata

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstudio.app_intro.R

class StudentdataFragment : Fragment() {

    companion object {
        fun newInstance() = StudentdataFragment()
    }

    private lateinit var viewModel: StudentdataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_studentdata, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentdataViewModel::class.java)
        // TODO: Use the ViewModel
    }

}