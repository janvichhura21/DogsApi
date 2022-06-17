package com.example.dogsapi.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapi.databinding.LoadDataBinding

class LoadingStateAdapter(private val retry: ()-> Unit): LoadStateAdapter<LoadingStateAdapter.LoadViewHolder>() {

    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
       return LoadViewHolder(LoadDataBinding.inflate(LayoutInflater.from(parent.context),parent,false),retry)
    }

    class LoadViewHolder(private val binding: LoadDataBinding
                         ,retry: ()->Unit):RecyclerView.ViewHolder(binding.root){
        init {
            binding.retryBtn.setOnClickListener {
                retry()
            }
        }
         fun bind(loadState: LoadState){
             binding.apply {
                 progressBar.isVisible = loadState is LoadState.Loading
                 retryBtn.isVisible =loadState !is LoadState.Loading
                 errorTxt.isVisible =loadState !is LoadState.Loading
             }
         }
    }
}