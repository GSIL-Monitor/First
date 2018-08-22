package com.example.wuxiangyu.first

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : Activity() {
    lateinit var rv: RecyclerView
    var strList: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.recyclerview)
        val layoutmanager = LinearLayoutManager(this)
//        layoutmanager.initialPrefetchItemCount = 5
        layoutmanager.isItemPrefetchEnabled = false;
        val adapter = MainAdapter()
//        (rv?.itemAnimator as SimpleItemAnimator)?.supportsChangeAnimations = false
        rv.itemAnimator = null
//        rv.itemAnimator.moveDuration = 0
        rv.adapter = adapter
        rv.animation = null
        rv.layoutManager = layoutmanager
        adapter.setTemp(getList())
        adapter.setListener (object : MainAdapter.MyClick {
            override fun clickBefore(currentPosition: Int) {
                adapter.removeIndex(currentPosition)
                adapter.notifyItemRemoved(currentPosition)
            }

            override fun clickAfter(currentPosition: Int) {
                rv.smoothScrollToPosition(currentPosition + 1)
            }
        })

        val cl = this.findViewById<ConstraintLayout>(R.id.clClick)
        cl.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.e("xxxx", "onclick")
            }

        })

        val btnClick = findViewById<Button>(R.id.btnClick)
        btnClick.setOnClickListener {v -> Log.e("xxxx", "onclick")}
        val snapHelper = PagerSnapHelper ()
        snapHelper.attachToRecyclerView(rv)
//        rv.postDelayed({
//            rv.smoothScrollToPosition(5)
//        }, 2000)
        btnClick.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                Log.e("xxxx", "onLongClick")
                return false;
            }

        })
    }
    fun getList(): List<String> {
        val list: MutableList<String> = ArrayList<String>()
        for (i in 1..10) {
            list.add("" + i)
        }
        return list
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
