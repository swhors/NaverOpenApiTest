package com.simpson.goodssearch.ui.mydata

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.MainActivity
import com.simpson.goodssearch.databinding.FragmentMyDataBinding
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteHelper
import com.simpson.goodssearch.ui.search.naver.goods.RecyclerViewAdapter
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FragmentMyData : Fragment() {
    private var _binding: FragmentMyDataBinding? = null
    private lateinit var viewModel: FragmentMyDataViewModel

    private var sqLiteCtl: SQLiteCtl ?= null
    private var sqLiteHelper: SQLiteHelper ?= null
    private lateinit var recyclerView: RecyclerView
    private var imm: InputMethodManager ?= null
    private var viewUpdatetor: Thread ?= null
    private var isRun: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        sqLiteCtl = (activity as MainActivity).sqLiteCtl()
        sqLiteHelper = (activity as MainActivity).sqLiteHelper()!!

        super.onCreate(savedInstanceState)
    }

    private val binding get() = _binding!!

    private fun updateView() {
        var results = this.sqLiteCtl?.select(-1)

        println("$results")

        (recyclerView.adapter as RecyclerViewAdapter).clear()
        for(result in results!!) {
            println("$result")
            (recyclerView.adapter as RecyclerViewAdapter).
            addItem(result.name, result.lprice, result.hprice, result.url, result.image)
        }
        (recyclerView.adapter as RecyclerViewAdapter).notifyDataSetChanged()
        Log.i("FragmentMyData", "onCreateView")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyDataBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.myDatas
        if (recyclerView.layoutManager == null) {
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = sqLiteCtl?.let { RecyclerViewAdapter(it) }
        }

        if (imm == null)
            imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        isRun = true

        CoroutineScope(Main).launch {
            do {
                updateView()
                delay(60000)
            } while(isRun)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentMyDataViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentMyData", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("FragmentMyData", "onPause")
        isRun = false
    }

}