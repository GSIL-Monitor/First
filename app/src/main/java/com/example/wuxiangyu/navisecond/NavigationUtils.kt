package com.example.wuxiangyu.navisecond

import android.util.SparseArray
import androidx.navigation.fragment.NavHostFragment

class NavigationUtils {
    private val fragments = SparseArray<NavHostFragment>()
    fun getNavHostFragment(navId: Int): NavHostFragment {
        var instance = fragments.get(navId, null)
        if (instance == null) {
            instance = NavHostFragment()
            instance.setGraph(navId)
            fragments.put(navId, instance)
        }
        return instance
    }
}