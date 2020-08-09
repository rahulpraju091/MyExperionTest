package com.work.experion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.work.experion.model.FeatureModel
import kotlinx.android.synthetic.main.item_feature.view.*

/**
 * Adapter class.
 *
 * This class handles recyclerview item design and data.
 *
 * @param items is feature list.
 */
class FeaturesListAdapter(var items: List<FeatureModel?>) :
    RecyclerView.Adapter<FeaturesListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /** bind function has bind the data with layout controls.
         * Set title, description and image data.
         */
        fun bind(item: FeatureModel?) {
            item.let {
                itemView.txt_title.text = it?.title
                itemView.txt_desc.text = it?.description
                Glide.with(itemView.context).load(it?.imageHref)
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .into(itemView.img_feature)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_feature, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = this.items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /** Calling ViewHolder class bind function and passing single item from list
         */
        holder.bind(this.items.get(holder.adapterPosition))
    }

}