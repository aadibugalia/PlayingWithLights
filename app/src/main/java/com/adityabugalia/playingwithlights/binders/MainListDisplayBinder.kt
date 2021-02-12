package com.adityabugalia.itunespublicapi.viewbinders

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.itunespublicapi.adapters.AbstractRecyclerViewAdapter
import com.adityabugalia.itunespublicapi.viewholders.MainListDisplayViewHolder
import com.adityabugalia.playingwithlights.databinding.MainListItemStructureBinding
import com.adityabugalia.playingwithlights.models.DeviceModel
import com.adityabugalia.playingwithlights.viewmodels.MainActivityViewModel

class MainListDisplayBinder(
    private val model: DeviceModel,
    val viewModel: MainActivityViewModel
) : AbstractRecyclerViewAdapter.Binder<MainListDisplayViewHolder> {

    override fun bind(holder: MainListDisplayViewHolder) {
        holder.itemView
        holder.bind(model)
    }

    override fun getViewType(): Int {
        return this.javaClass.hashCode()
    }

    override fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var binding =
            MainListItemStructureBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.deviceModel = model
        binding.viewModel = viewModel
        return MainListDisplayViewHolder(binding)
    }
}
