package com.example.leaguechampionstest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil3.load
import coil3.request.placeholder
import coil3.request.error
import com.example.leaguechampionstest.R
import com.example.leaguechampionstest.data.Champion
import com.example.leaguechampionstest.data.ChampionInMemoryDataService
import com.example.leaguechampionstest.data.DataService
import com.example.leaguechampionstest.databinding.FragmentChampionDetailsBinding

class ChampionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentChampionDetailsBinding
    private val args: ChampionDetailsFragmentArgs by navArgs()
    private val championsDataService: DataService<Champion> = ChampionInMemoryDataService.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val championId: Int = args.championId
        val champion: Champion = championsDataService.requestOne(championId)
        binding.championName.text = champion.name
        binding.championTitle.text = champion.title
        binding.championLore.text = champion.lore
        binding.championImage.load(
            champion.imageUrl
        ) {
            placeholder(R.drawable.loading)
            error(R.drawable.loading)
        }
        binding.returnButton.setOnClickListener { findNavController().popBackStack() }
    }

}