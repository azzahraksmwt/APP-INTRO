package com.androidstudio.app_intro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.androidstudio.app_intro.databinding.FragmentHomeBinding
import com.androidstudio.app_intro.listgoods.AdapterGoods
import com.androidstudio.app_intro.listgoods.GoodsData

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<GoodsData>
    lateinit var imageList: Array<Int>
    lateinit var itemList: Array<String>

    private fun getData(){
        for (i in imageList.indices){
            val GoodsData = GoodsData(imageList[i], itemList[i])
            dataList.add((GoodsData))
        }
        recyclerView.adapter = AdapterGoods(dataList)
    }

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}