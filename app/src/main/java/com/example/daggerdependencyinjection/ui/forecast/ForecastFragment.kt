package com.example.daggerdependencyinjection.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.daggerdependencyinjection.databinding.FragmentMainBinding
import com.example.daggerdependencyinjection.di.modules.ViewModelFactory
import com.example.daggerdependencyinjection.ui.forecast.recyclerView.DaysAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ForecastFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ForecastViewModel by lazy { viewModelFactory.create(ForecastViewModel::class.java) }

    private lateinit var binding: FragmentMainBinding
    private val adapter = DaysAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getForecast()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.forecastLiveData.observe(viewLifecycleOwner) {
            adapter.refreshForecastList(it)
        }
    }
}