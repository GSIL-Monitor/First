package com.example.wuxiangyu.gank

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.wuxiangyu.first.R

class GankAdapter(val context: Context, val onclickListener: GankItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<GankAndroidItemBean>()
    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.item_gank, parent, false)
        return ViewHolder(view)
    }

    fun refresh(addList: List<GankAndroidItemBean>) {
        list.clear()
        list.addAll(addList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getItem(position: Int): GankAndroidItemBean {
        return list[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gankAndroidItemBean = getItem(position)
        when (holder) {
            is ViewHolder -> {
                with(gankAndroidItemBean) {
                    holder.tvAuthor.text = who
                    holder.tvDesc.text = desc
                    holder.tvType.text = type
                    holder.tvTime.text = createdAt
                    holder.tvAuthor.tag = gankAndroidItemBean
                }
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAuthor = view.findViewById<TextView>(R.id.tvAuthor)
        val tvTime = view.findViewById<TextView>(R.id.tvTime)
        val tvDesc = view.findViewById<TextView>(R.id.tvDesc)
        val tvType = view.findViewById<TextView>(R.id.tvType)
        val ivAuthor = view.findViewById<ImageView>(R.id.ivAuthor)
        init {
            view.setOnClickListener{
                onclickListener.itemClick(tvAuthor.tag as GankAndroidItemBean)
            }
        }

    }
    interface GankItemClickListener {
        fun itemClick(bean: GankAndroidItemBean)
    }
}