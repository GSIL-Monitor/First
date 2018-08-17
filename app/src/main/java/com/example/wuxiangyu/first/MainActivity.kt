package com.example.wuxiangyu.first

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView

class MainActivity : Activity() {
    lateinit var rv: RecyclerView
    var strList: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.recyclerview)
        val layoutmanager = LinearLayoutManager(this)
        layoutmanager.initialPrefetchItemCount = 5
        val adapter = MainAdapter()
        rv.adapter = adapter
        rv.layoutManager = layoutmanager
        adapter.setTemp(getList())
        adapter.setListener (object : MainAdapter.MyClick {
            override fun clickBefore(currentPosition: Int) {
                rv.smoothScrollToPosition(currentPosition - 1)
            }

            override fun clickAfter(currentPosition: Int) {
                rv.smoothScrollToPosition(currentPosition + 1)
            }
        })

        val snapHelper = PagerSnapHelper ()
        snapHelper.attachToRecyclerView(rv)
//        rv.postDelayed({
//            rv.smoothScrollToPosition(5)
//        }, 2000)
    }
    fun getList(): List<String> {
        return listOf<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

        fun launch(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }


}
