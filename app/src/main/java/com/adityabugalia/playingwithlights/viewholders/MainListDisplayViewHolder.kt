package com.adityabugalia.itunespublicapi.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.playingwithlights.BR
import com.adityabugalia.playingwithlights.databinding.MainListItemStructureBinding
import com.adityabugalia.playingwithlights.models.DeviceModel

class MainListDisplayViewHolder(val binding: MainListItemStructureBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: DeviceModel) {
        binding.setVariable(BR.deviceModel,data)
        binding.executePendingBindings()
    }
}