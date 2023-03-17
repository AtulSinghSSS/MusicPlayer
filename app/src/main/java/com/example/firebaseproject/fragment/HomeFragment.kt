package com.example.firebaseproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebaseproject.R
import com.example.firebaseproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var mBinding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        init()
        return view
    }

    private fun init() {

    }

}