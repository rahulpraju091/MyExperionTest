package com.work.experion

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.work.experion.model.FeatureModel
import kotlinx.android.synthetic.main.item_feature.view.*


class FeaturesListAdapter(var items: List<FeatureModel?>) :
    RecyclerView.Adapter<FeaturesListAdapter.ViewHolder>() {

    companion object {
        val TAG: String = FeaturesListAdapter::class.java.simpleName.toString()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: FeatureModel?) {
            item.let {
                Log.d(TAG, "Item:::$it")
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
        holder.bind(this.items.get(holder.adapterPosition))
    }

}