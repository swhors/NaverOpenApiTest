package com.simpson.goodssearch.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.simpson.goodssearch.MainActivity
import com.simpson.goodssearch.databinding.FragmentHomeBinding
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var trendViewModel: trendViewModel
    private var _binding: FragmentHomeBinding? = null
    private var _spltteCtl : SQLiteCtl ?= null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this._spltteCtl = (activity as MainActivity).sqLiteCtl()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        trendViewModel =
                ViewModelProvider(this).get(trendViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val trendRecyclerView = root.trend_list_view
        val hotRecyclerView = root.hot_list_view
        trendRecyclerView.adapter = this._spltteCtl?.let { RecyclerViewTrendAdapter(it) }
        hotRecyclerView.adapter = this._spltteCtl?.let { RecyclerViewHotAdapter(it) }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}