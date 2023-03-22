package com.example.firebaseproject.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseproject.R
import com.example.firebaseproject.activity.HomeActivity
import com.example.firebaseproject.adapter.ContinueListeningAdapter
import com.example.firebaseproject.adapter.RecentListeningAdapter
import com.example.firebaseproject.adapter.TopMixesAdapter
import com.example.firebaseproject.appRepository.AppRepository
import com.example.firebaseproject.appRepository.MyApplication
import com.example.firebaseproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var mBinding:FragmentHomeBinding
    private lateinit var homePageViewModel: HomePageViewModel
    private lateinit var continueListeningAdapter: ContinueListeningAdapter
    private lateinit var topMixesAdapter:TopMixesAdapter
    private lateinit var recentListeningAdapter: RecentListeningAdapter
    private lateinit var activity: HomeActivity
    private lateinit var appCtx: MyApplication


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as HomeActivity
        appCtx = activity.application as MyApplication
    }
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
        var appRepository = AppRepository(activity.applicationContext)
        homePageViewModel =
            ViewModelProvider(
                activity,
                HomePageViewModelFactory(
                    appCtx,
                    appRepository
                )
            )[HomePageViewModel::class.java]
//        observe(homePageViewModel ::handleResult)
       /* var model = DataModel(1,"Atul")

        homePageViewModel.insertData(model)*/
        Log.e("TAG", "Atul " )
        continueListeningAdapter= ContinueListeningAdapter(this)
        mBinding.rvListView.adapter = continueListeningAdapter

        topMixesAdapter= TopMixesAdapter(this)
        mBinding.rvListView2.adapter = topMixesAdapter

        recentListeningAdapter= RecentListeningAdapter(this)
        mBinding.rvListView3.adapter = recentListeningAdapter
    }



}