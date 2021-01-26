package com.simpson.goodssearch

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.simpson.goodssearch.databinding.ActivityMainBinding
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteHelper
import com.simpson.goodssearch.ui.mydata.FragmentMyDataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val app_name = "MySearch"

    private var sqLiteCtl: SQLiteCtl?= null
    private var sqLiteHelper: SQLiteHelper?= null

    fun sqLiteCtl() = this.sqLiteCtl
    fun sqLiteHelper() = this.sqLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sqLiteHelper = SQLiteHelper(this, SQLiteHelper.table_name, null, 1)
        sqLiteCtl = SQLiteCtl(sqLiteHelper!!)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.nav_my_data,
            R.id.nav_search_data,
            R.id.nav_trend))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}