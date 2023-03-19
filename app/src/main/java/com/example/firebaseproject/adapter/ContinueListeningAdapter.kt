package com.example.firebaseproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.R
import com.example.firebaseproject.databinding.ContinueListeningBinding
import com.example.firebaseproject.fragment.HomeFragment

class ContinueListeningAdapter(homeFragment: HomeFragment) : RecyclerView.Adapter<ContinueListeningAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.continue_listening,
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

    class ViewHolder(var mBinding: ContinueListeningBinding) :
        RecyclerView.ViewHolder(mBinding.root)
}