package com.simpson.goodssearch.ui.search.naver.goods

import android.content.Context
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
import com.simpson.goodssearch.MainActivity
import com.simpson.goodssearch.databinding.FragmentSearchDataBinding
import com.simpson.goodssearch.domain.model.naver.NaverDataModelImpl
import com.simpson.goodssearch.domain.model.naver.data.common.Sort
import com.simpson.goodssearch.domain.model.naver.service.NaverSearchService
import android.util.Log
import com.simpson.goodssearch.domain.model.mygoods.sqlite.MyGoods
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteHelper

class FragmentSearch : Fragment() {
    private lateinit var naverViewModel: FragmentSearchViewModel

    private var _binding: FragmentSearchDataBinding? = null
    private val binding get() = _binding!!

    private var imm: InputMethodManager? = null

    var sqLiteCtl: SQLiteCtl?= null
    lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        sqLiteCtl = (activity as MainActivity).sqLiteCtl()
        sqLiteHelper = (activity as MainActivity).sqLiteHelper()!!

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        naverViewModel =
            FragmentSearchViewModel(
                NaverDataModelImpl(NaverSearchService.create())
            )

        _binding = FragmentSearchDataBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val btn: Button = binding.btnSearch
        val goodsName: TextView = binding.textGoodsName
        val sortType: TextView = binding.textSort
        val itemCnt: TextView = binding.textItemCntMax
        val recyclerView: RecyclerView = binding.viewResults
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = sqLiteCtl?.let { RecyclerViewAdapter(it) }

        val imm: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        var cnt = 0

        naverViewModel.naverGoodsResponseLiveData.observe(viewLifecycleOwner, {
            println("result = ${it}, cnt =$cnt")
            (recyclerView.adapter as RecyclerViewAdapter).clear()
            (recyclerView.adapter as RecyclerViewAdapter).notifyItemRemoved(0)
            it.items.forEach {
                    item ->
                    println("title=${item.title}, lprice=${item.lprice}, hprice=${item.hprice}, i=${cnt++}")
                (recyclerView.adapter as RecyclerViewAdapter).addItem(
                    MyGoods.Builder()
                        .goods_name(item.title!!)
                        .image_url(item.image!!)
                        .goods_url(item.link!!)
                        .mall_name(if(item.malName==null) "" else item.malName!!)
                        .lprice(item.lprice!!)
                        .hprice(item.hprice!!)
                        .goods_id(if(item.productId != null) item.productId.toString().toLong() else 0L)
                        .builder())
            }
            (recyclerView.adapter as RecyclerViewAdapter).notifyDataSetChanged()
        })

        btn.setOnClickListener{
            println("Goods = ${goodsName.text}")
            println("Count = $itemCnt")
            println("Count = ${itemCnt.text}")

            naverViewModel.search(goodsName.text.toString(), 10, 10, Sort.ASC)
            imm.hideSoftInputFromWindow(goodsName.windowToken, 0)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
