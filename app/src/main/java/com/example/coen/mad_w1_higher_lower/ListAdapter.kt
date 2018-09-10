package com.example.coen.mad_w1_higher_lower

/**
 * Created by coen on 10/09/2018.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(val items : ArrayList<Int>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tvInfo?.text = "Throw is " + items.get(position)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvInfo = view.tv_info
}
