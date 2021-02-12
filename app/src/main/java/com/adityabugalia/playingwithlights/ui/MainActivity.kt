package com.adityabugalia.playingwithlights.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityabugalia.itunespublicapi.adapters.MainListDisplayAdapter
import com.adityabugalia.playingwithlights.R
import com.adityabugalia.playingwithlights.databinding.ActivityMainBinding
import com.adityabugalia.playingwithlights.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: MainListDisplayAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


    override fun onStart() {
        super.onStart()

        initViewModel()
    }


    private fun initAdapter() {
        adapter = MainListDisplayAdapter(viewModel, this)
        val mLayoutManager = LinearLayoutManager(applicationContext)

        binding.mainDisplayRV.adapter = adapter
        binding.mainDisplayRV.setLayoutManager(mLayoutManager)
        adapter.reloadData()
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.onViewReady()
        // initAdapter()
        viewModel.onDeviceSatusUpdate.observe(
            this,
            Observer { result ->
                adapter.reloadData()
            }
        )

        viewModel.onViewReadyLiveData.observe(
            this,
            Observer { result ->
                if (result) {
                    initAdapter()
                }

            }
        )
        viewModel.onMainBrightnessChange.observe(
            this,
            Observer { result ->
                binding.brightnessBarSB.progress = result

            }
        )
        viewModel.onMainDeviceTurnOff.observe(
            this,
            Observer { result ->
                showToast(if(result==true) "All Devices Turn On" else "All devices Turned off")

            }
        )

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}