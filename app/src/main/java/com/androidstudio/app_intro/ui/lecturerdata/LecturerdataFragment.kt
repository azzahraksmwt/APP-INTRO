package com.androidstudio.app_intro.ui.lecturerdata

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.androidstudio.app_intro.R
import com.androidstudio.app_intro.databinding.FragmentLecturerdataBinding

class LecturerdataFragment : Fragment() {

    private var _binding: FragmentLecturerdataBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLecturerdataBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val lecturerDataViewModel =
            ViewModelProvider(this)[LecturerdataViewModel::class.java]

        // Menambahkan onClickListener pada tombol "Add"
        binding.btnadd.setOnClickListener {
            // Menggunakan NavController untuk navigasi ke AddLecturerDataFragment
            findNavController().navigate(R.id.fragment_add_lecturer_data)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}