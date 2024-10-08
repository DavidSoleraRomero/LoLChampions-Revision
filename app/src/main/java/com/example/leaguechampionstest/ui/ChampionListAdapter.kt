package com.example.leaguechampionstest.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.load
import coil3.request.placeholder
import coil3.request.error
import com.example.leaguechampionstest.R
import com.example.leaguechampionstest.data.Champion
import com.example.leaguechampionstest.databinding.ChampionViewBinding

class ChampionListAdapter(
    private val toChampionDetail: ((Champion) -> Unit)
): ListAdapter<Champion, ChampionListAdapter.ChampionViewHolder>(ChampionComparer) {

    inner class ChampionViewHolder(
        private val binding: ChampionViewBinding
    ): ViewHolder(binding.root) {

        fun bind(champion: Champion) {
            binding.championName.text = champion.name
            binding.championTitle.text = champion.title
            binding.championImage.load(
                champion.imageUrl
            ) {
                placeholder(R.drawable.loading)
                error(R.drawable.loading)
            }
            binding.root.setOnClickListener { toChampionDetail(champion) }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChampionListAdapter.ChampionViewHolder {
        val binding = ChampionViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChampionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        val champion: Champion = getItem(position)
        holder.bind(champion)
    }

    object ChampionComparer: DiffUtil.ItemCallback<Champion>() {

        override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean =
            oldItem.name == newItem.name && oldItem.title == newItem.title && oldItem.lore == newItem.lore

    }

}
