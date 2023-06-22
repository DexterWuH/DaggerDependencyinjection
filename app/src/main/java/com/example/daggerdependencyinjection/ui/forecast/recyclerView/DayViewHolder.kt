package com.example.daggerdependencyinjection.ui.forecast.recyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.daggerdependencyinjection.R
import com.example.daggerdependencyinjection.data.DayForecast

class DayViewHolder(private val view: View) : ViewHolder(view) {

    fun bindData(data: DayForecast) {
        val forecastText: TextView = view.findViewById(R.id.average_temperature)

        val text = data.descriptions.first().detailedDescription
        forecastText.text = text
    }
}
