package com.example.daggerdependencyinjection.ui.forecast.recyclerView

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.daggerdependencyinjection.R
import com.example.daggerdependencyinjection.data.DayForecast

class DaysAdapter(initialDaysForecast: List<DayForecast>) : Adapter<DayViewHolder>() {

    private val daysForecast: MutableList<DayForecast> = mutableListOf<DayForecast>().apply {
        addAll(initialDaysForecast)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshForecastList(newForecast: List<DayForecast>) {
        daysForecast.clear()
        daysForecast.addAll(newForecast)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = View.inflate(parent.context, R.layout.view_holder_day_forecast, null)
        return DayViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daysForecast.size
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bindData(daysForecast[holder.adapterPosition])
    }
}