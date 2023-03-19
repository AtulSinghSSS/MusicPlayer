package com.example.firebaseproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.R
import com.example.firebaseproject.databinding.TopMixesListeningBinding
import com.example.firebaseproject.fragment.HomeFragment

class TopMixesAdapter(homeFragment: HomeFragment) : RecyclerView.Adapter<TopMixesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.top_mixes_listening,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      //  val model = list!![position]

    }

    override fun getItemCount(): Int {
        return 15
    }

    class ViewHolder(var mBinding: TopMixesListeningBinding) :
        RecyclerView.ViewHolder(mBinding.root)
}