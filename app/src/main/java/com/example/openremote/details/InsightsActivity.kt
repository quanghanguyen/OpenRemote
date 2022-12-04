package com.example.openremote.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openremote.R
import com.example.openremote.adapter.InsightAdapter
import com.example.openremote.databinding.ActivityInsightsBinding
import com.example.openremote.model.InsightModel

class InsightsActivity : AppCompatActivity() {

    private var _binding : ActivityInsightsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInsightsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<InsightModel>()
        for (i in 15..20) {
            data.add(InsightModel("$i kW"))
        }
        val adapter = InsightAdapter(data)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}