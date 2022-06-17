package com.example.dogsapi.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogsapi.databinding.EachRowBinding
import com.example.dogsapi.model.Dogs

class DogsAdapter:PagingDataAdapter<Dogs,DogsAdapter.DogsViewHolder>(diff) {
   inner class DogsViewHolder(val binding: EachRowBinding):RecyclerView.ViewHolder(binding.root){
       fun bind(data:Dogs){
           binding.apply {
               pic.load(data.url)
           }
       }
    }
    object diff:DiffUtil.ItemCallback<Dogs>(){
        override fun areItemsTheSame(oldItem: Dogs, newItem: Dogs): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Dogs, newItem: Dogs): Boolean {
            return oldItem==newItem
        }

    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
       holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
       return DogsViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}