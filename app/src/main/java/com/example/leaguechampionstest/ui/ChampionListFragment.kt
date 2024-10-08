package com.example.leaguechampionstest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.leaguechampionstest.R
import com.example.leaguechampionstest.data.Champion
import com.example.leaguechampionstest.data.ChampionInMemoryDataService
import com.example.leaguechampionstest.data.DataService
import com.example.leaguechampionstest.databinding.FragmentChampionListBinding

class ChampionListFragment : Fragment() {

    private lateinit var binding: FragmentChampionListBinding
    private val championsDataService: DataService<Champion> = ChampionInMemoryDataService.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.championsList
        recyclerView.adapter = ChampionListAdapter { champion: Champion ->
            val action = ChampionListFragmentDirections.actionChampionListFragmentToChampionDetailsFragment(champion.id)
            findNavController().navigate(action)
        }
        (recyclerView.adapter as ChampionListAdapter).submitList(championsDataService.requestAll())
    }

}