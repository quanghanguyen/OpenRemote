package com.example.openremote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openremote.databinding.InsightsItemBinding
import com.example.openremote.model.InsightModel

class InsightAdapter(private val mList: List<InsightModel>) : RecyclerView.Adapter<InsightAdapter.MyViewHolder>() {

    class MyViewHolder(private val itemInsightBinding : InsightsItemBinding) : RecyclerView.ViewHolder(itemInsightBinding.root) {
        fun bind (data : InsightModel) {
            with(itemInsightBinding) {
                km.text = data.km
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val items = InsightsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(items)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size;
    }
}