package com.simpson.goodssearch.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.simpson.goodssearch.MainActivity
import com.simpson.goodssearch.databinding.FragmentHomeBinding
import com.simpson.goodssearch.domain.model.mygoods.api.model.ApiCategoryModelImpl
import com.simpson.goodssearch.domain.model.mygoods.api.model.ApiGoodsModelImpl
import com.simpson.goodssearch.domain.model.mygoods.api.service.CategoryApiService
import com.simpson.goodssearch.domain.model.mygoods.api.service.GoodsApiService
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import com.simpson.goodssearch.ui.mydata.FragmentMyDataViewModel
import com.simpson.goodssearch.ui.mydata.GoodsRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var trendViewModel: TrendViewModel
    private lateinit var hotViewModel: HotViewModel
    private var _binding: FragmentHomeBinding? = null
    private var _spltteCtl : SQLiteCtl ?= null

    private lateinit var viewModel: TrendViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this._spltteCtl = (activity as MainActivity).sqLiteCtl()
    }

    private fun updateView() {
        trendViewModel.getData()
        Log.i("FragmentMyData", "onCreateView")
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        Log.i("HomeFragment", "onCreateView")
        hotViewModel =
            HotViewModel(ApiGoodsModelImpl(GoodsApiService.create()))
        trendViewModel =
            TrendViewModel(ApiCategoryModelImpl(CategoryApiService.create()))

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val trendRecyclerView = root.trend_list_view
        val hotRecyclerView = root.hot_list_view
        if (trendRecyclerView.layoutManager == null) {
            trendRecyclerView.layoutManager = LinearLayoutManager(activity)
            trendRecyclerView.adapter = RecyclerViewTrendAdapter()
        }

        if (hotRecyclerView.layoutManager == null) {
            hotRecyclerView.layoutManager = LinearLayoutManager(activity)
            hotRecyclerView.adapter = RecyclerViewHotAdapter()
        }

        hotViewModel.goodssResponse.observe(viewLifecycleOwner, {
            println("result = ${it.len}, ${it.goods}")
            (hotRecyclerView.adapter as RecyclerViewHotAdapter).clear()
            (hotRecyclerView.adapter as RecyclerViewHotAdapter).notifyItemRemoved(0)
            it.goods.forEach {
                println("${it.name}::${it.mall_name}::${it.lprice}::${it.hprice}")
                (hotRecyclerView.adapter as RecyclerViewHotAdapter).addItem(it)
            }
            (hotRecyclerView.adapter as RecyclerViewHotAdapter).notifyDataSetChanged()
        })

        trendViewModel.categoriesResponse.observe(viewLifecycleOwner, {
            println("result = ${it.len}, ${it.categories}")
            (trendRecyclerView.adapter as RecyclerViewTrendAdapter).clear()
            (trendRecyclerView.adapter as RecyclerViewTrendAdapter).notifyItemRemoved(0)
            it.categories.forEach {
                println("${it.title}::${it.cnt}")
                (trendRecyclerView.adapter as RecyclerViewTrendAdapter).addItem(it)
            }
            (trendRecyclerView.adapter as RecyclerViewTrendAdapter).notifyDataSetChanged()
        })

        updateView()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}