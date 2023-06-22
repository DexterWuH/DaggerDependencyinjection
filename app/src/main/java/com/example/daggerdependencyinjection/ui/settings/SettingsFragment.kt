package com.example.daggerdependencyinjection.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.daggerdependencyinjection.databinding.FragmentSettingsBinding
import com.example.daggerdependencyinjection.di.modules.ViewModelFactory
import com.example.daggerdependencyinjection.repositories.Coordinates
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: SettingsViewModel by lazy { viewModelFactory.create(SettingsViewModel::class.java) }

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changeCoordinatesNameButton.setOnClickListener {
            val latitude = binding.latitudeEditText.text
            val longitude = binding.longitudeEditText.text
            val coordinates = Coordinates(latitude.toString(), longitude.toString())
            viewModel.setUserPreferredCoordinates(coordinates)
            binding.latitudeEditText.text.clear()
            binding.longitudeEditText.text.clear()
            parentFragmentManager.popBackStack()
        }
    }
}