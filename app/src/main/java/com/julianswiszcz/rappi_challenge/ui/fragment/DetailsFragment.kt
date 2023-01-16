package com.julianswiszcz.rappi_challenge.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.julianswiszcz.rappi_challenge.ui.vm.DetailsViewModel
import com.julianswiszcz.rappi_challenge.R
import com.julianswiszcz.rappi_challenge.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailsBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setMovieId(args.movieId)

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            binding.txtTitle.text = it.data?.title
            it.data?.image?.let { image ->
                Picasso.get().load(IMAGE_URL + image).into(binding.img)
            }
            binding.txtDescription.text = it.data?.description
        }

        viewModel.movieVideos.observe(viewLifecycleOwner) { data ->
            binding.txtTrailer.setOnClickListener {
                data.data?.first()?.key?.let { key ->
                    val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$key"))
                    val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$key"))
                    try {
                        this.startActivity(intentApp)
                    } catch (ex: ActivityNotFoundException) {
                        this.startActivity(intentBrowser)
                    }
                }
            }
        }
    }

    companion object {
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/original"
    }
}
