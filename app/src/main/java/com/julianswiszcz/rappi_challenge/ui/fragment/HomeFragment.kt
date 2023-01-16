package com.julianswiszcz.rappi_challenge.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.julianswiszcz.rappi_challenge.ui.vm.HomeViewModel
import com.julianswiszcz.rappi_challenge.MovieAdapter
import com.julianswiszcz.rappi_challenge.R
import com.julianswiszcz.rappi_challenge.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), MovieAdapter.CallBack {

    private lateinit var binding: FragmentHomeBinding
    private val adapterUpcoming: MovieAdapter = MovieAdapter(this)
    private val adapterTopRated: MovieAdapter = MovieAdapter(this)
    private val adapterRecommended: MovieAdapter = MovieAdapter(this)
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerUpcoming.adapter = adapterUpcoming
        binding.recyclerTopRated.adapter = adapterTopRated
        binding.recyclerRecommended.adapter = adapterRecommended

        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            adapterUpcoming.submitList(it.data)
        }
        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            adapterTopRated.submitList(it.data)
        }

        viewModel.recommendedMovies.observe(viewLifecycleOwner) {
            adapterRecommended.submitList(it.data)
        }

        binding.txtFilterLanguage.setOnClickListener {
            viewModel.setFilterLanguage()
        }

        binding.txtFilterYear.setOnClickListener {
            viewModel.setFilterYear()
        }

        viewModel.filterYear.observe(viewLifecycleOwner) {
            if (it) {
                binding.txtFilterYear.setBackgroundResource(R.drawable.bkg_white_rounded)
                binding.txtFilterYear.setTextColor(Color.BLACK)
            } else {
                binding.txtFilterYear.setBackgroundResource(R.drawable.bkg_black_rounded)
                binding.txtFilterYear.setTextColor(Color.WHITE)
            }
        }

        viewModel.filterLanguage.observe(viewLifecycleOwner) {
            if (it) {
                binding.txtFilterLanguage.setBackgroundResource(R.drawable.bkg_white_rounded)
                binding.txtFilterLanguage.setTextColor(Color.BLACK)
            } else {
                binding.txtFilterLanguage.setBackgroundResource(R.drawable.bkg_black_rounded)
                binding.txtFilterLanguage.setTextColor(Color.WHITE)
            }
        }
    }

    override fun onMovieClick(movieId: Int) {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_home_to_details, bundleOf("movieId" to movieId))
    }
}
