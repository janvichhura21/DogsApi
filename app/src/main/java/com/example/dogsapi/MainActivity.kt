package com.example.dogsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dogsapi.databinding.ActivityMainBinding
import com.example.dogsapi.model.adapter.DogsAdapter
import com.example.dogsapi.model.adapter.LoadingStateAdapter
import com.example.dogsapi.model.viewModel.DogsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dogsAdapter: DogsAdapter
    private val viewModel:DogsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dogsAdapter= DogsAdapter()
        setUpRv()
        lifecycleScope.launchWhenStarted {
            viewModel.getDogs.collectLatest {
                binding.apply {
                    rv.isVisible=true
                    progressBar.isVisible=false
                }
                dogsAdapter.submitData(it)
            }
        }
    }

    private fun setUpRv() {
        binding.rv.apply {
            setHasFixedSize(true)
            layoutManager=GridLayoutManager(this@MainActivity,2)
            adapter=dogsAdapter.withLoadStateHeaderAndFooter(
                header = LoadingStateAdapter { dogsAdapter.retry() },
                footer = LoadingStateAdapter { dogsAdapter.retry() }
            )
        }
    }
}