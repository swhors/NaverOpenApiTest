package com.simpson.goodssearch.ui.search.naver.trend

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.databinding.FragmentTrendBinding
import com.simpson.goodssearch.domain.model.naver.NaverDataModelImpl
import com.simpson.goodssearch.domain.model.naver.service.NaverSearchService

class FragmentNavTrendApi : Fragment() {
    private lateinit var trendViewModel: FragmentNavTrendApiViewModel

    private var _binding: FragmentTrendBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        trendViewModel =
            FragmentNavTrendApiViewModel(
                NaverDataModelImpl(NaverSearchService.create())
            )

        _binding = FragmentTrendBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btn: Button = binding.btnSearchTrend
        val viewWebName: TextView = binding.textTrendKeyword
        val recyclerView: RecyclerView = binding.viewResults
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = RecyclerViewAdapter()

        var cnt = 0

        val imm: InputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        trendViewModel.naverGoodsResponseLiveData.observe(viewLifecycleOwner, {
            val lists = ArrayList<TrendCategoryAdapter.TrendItem>()
            var i = 0
            println("result = cnt =${cnt++}, ${it.results}")
            (recyclerView.adapter as RecyclerViewAdapter).clear()
            it.results.forEach {
                    result ->
                for (data in result.data) {
                    if ( i < 3) {
                        println("title=${result.title}, period=${data.period}, ratio=${data.ratio}, i=${i++}")
                        lists.add(
                            TrendCategoryAdapter.TrendItem(
                                result.title,
                                data.period,
                                data.ratio
                            )
                        )
                    }
                }
            }
            (recyclerView.adapter as RecyclerViewAdapter).addTrendItem(lists)
            (recyclerView.adapter as RecyclerViewAdapter).notifyDataSetChanged()
        })

        btn.setOnClickListener{
            println(viewWebName.text)
            trendViewModel.getWebSearch(viewWebName.text.toString(), 10,10)
            imm.hideSoftInputFromWindow(viewWebName.windowToken, 0)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
