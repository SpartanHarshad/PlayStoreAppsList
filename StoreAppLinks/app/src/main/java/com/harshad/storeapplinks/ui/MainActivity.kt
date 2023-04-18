package com.harshad.storeapplinks.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.harshad.storeapplinks.R
import com.harshad.storeapplinks.adapter.OnItemClick
import com.harshad.storeapplinks.adapter.StoreAppsAdapter
import com.harshad.storeapplinks.databinding.ActivityMainBinding
import com.harshad.storeapplinks.repository.StoreRepository
import com.harshad.storeapplinks.repository.remote.model.SubCategory
import com.harshad.storeapplinks.viewmodel.StoreViewModel
import com.harshad.storeapplinks.viewmodel.StoreViewModelFactory

class MainActivity : AppCompatActivity(), OnItemClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var storeViewModel: StoreViewModel
    private lateinit var storeAppsAdapter: StoreAppsAdapter
    private var subAppList = mutableListOf<SubCategory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView()
        initViewModel()
        checkInternet()
    }

    private fun checkInternet() {
        if (isNetworkAvailable(this)) {
            subAppList.clear()
            storeViewModel.getSubAppsList().observe(this) {
                subAppList.addAll(it)
                Log.d("ResponseList", "$it")
                binding.progressBar.visibility = View.GONE
                storeAppsAdapter.notifyDataSetChanged()
            }
        } else {
            Toast.makeText(this, "please check your internet", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        storeAppsAdapter = StoreAppsAdapter(subAppList, this, this)
        binding.rvAppStoreList.layoutManager = LinearLayoutManager(this)
        binding.rvAppStoreList.adapter = storeAppsAdapter
    }

    private fun initViewModel() {
        val storeRepo = StoreRepository()
        val storeFactory = StoreViewModelFactory(storeRepo)
        storeViewModel = ViewModelProvider(this, storeFactory)[StoreViewModel::class.java]
    }

    override fun onButtonClick(appLink: String?) {
        Toast.makeText(baseContext, "download link $appLink", Toast.LENGTH_SHORT).show()
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appLink")))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "App link not found", Toast.LENGTH_SHORT).show()
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}