package com.adityabugalia.itunespublicapi.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.playingwithlights.R
import kotlinx.android.synthetic.main.activity_main.view.*


class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun getLayoutId(): Int {
            return R.layout.main_list_item_structure
        }
    }
}