package com.example.firebaseproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.firebaseproject.R
import com.example.firebaseproject.databinding.ActivityHomeBinding
import com.example.firebaseproject.fragment.ExploreFragment
import com.example.firebaseproject.fragment.HomeFragment
import com.example.firebaseproject.fragment.LibraryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityHomeBinding
    lateinit var bottomNavigationView: BottomNavigationView
    var activityFlag = this.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_home)


        bottomNavigationView = mBinding.bottomNavigation
        val frag = HomeFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frameLayout , frag)
        transaction.commit()

        bottomNavigationView.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                var selectedFragment: Fragment? = null
                when (item.itemId) {
                    R.id.navHome ->
                        if (activityFlag != HomeFragment::class.java.simpleName)
                            selectedFragment = HomeFragment()
                    R.id.navExplore-> {
                        if (activityFlag != ExploreFragment::class.java.simpleName)
                            selectedFragment = ExploreFragment()
                    }
                    R.id.navLibrary -> {
                        if (activityFlag != LibraryFragment::class.java.simpleName)
                            selectedFragment = LibraryFragment()
                    }
                }
                if (selectedFragment != null) {
                    pushFragments(selectedFragment, true, true, null)
                }
                return@OnNavigationItemSelectedListener true
            }
        )

    }

    fun pushFragments(
        fragment: Fragment,
        shouldAnimate: Boolean,
        shouldAdd: Boolean,
        args: Bundle?
    ) {
        try {
            val manager = supportFragmentManager
            val fragmentPopped = manager.popBackStackImmediate(fragment.javaClass.simpleName, 0)
            if (!fragmentPopped) {  // fragment not in back stack, create it.
                val ft = manager.beginTransaction()
                if (args != null) {
                    fragment.arguments = args
                }
                if (shouldAdd) ft.addToBackStack(fragment.javaClass.simpleName)
                if (shouldAnimate) {
                    ft.setCustomAnimations(
                        R.anim.trans_left_in,
                        R.anim.trans_left_out,
                        R.anim.trans_right_in,
                        R.anim.trans_right_out
                    )
                }
                ft.replace(R.id.frameLayout, fragment)
                ft.commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}