package com.example.firebaseproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.firebaseproject.R
import com.example.firebaseproject.adapter.ContinueListeningAdapter
import com.example.firebaseproject.adapter.RecentListeningAdapter
import com.example.firebaseproject.adapter.TopMixesAdapter
import com.example.firebaseproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var mBinding:FragmentHomeBinding
    lateinit var continueListeningAdapter: ContinueListeningAdapter
    lateinit var topMixesAdapter:TopMixesAdapter
    lateinit var recentListeningAdapter: RecentListeningAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
            )
        init()
        return mBinding.root
    }

    private fun init() {
        continueListeningAdapter= ContinueListeningAdapter(this)
        mBinding.rvListView.adapter = continueListeningAdapter

        topMixesAdapter= TopMixesAdapter(this)
        mBinding.rvListView2.adapter = topMixesAdapter

        recentListeningAdapter= RecentListeningAdapter(this)
        mBinding.rvListView3.adapter = recentListeningAdapter
    }

}