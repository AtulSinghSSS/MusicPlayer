package com.example.firebaseproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.R
import com.example.firebaseproject.databinding.RecentListeningBinding
import com.example.firebaseproject.fragment.HomeFragment

class RecentListeningAdapter(homeFragment: HomeFragment) : RecyclerView.Adapter<RecentListeningAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recent_listening,
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

    class ViewHolder(var mBinding: RecentListeningBinding) :
        RecyclerView.ViewHolder(mBinding.root)
}
