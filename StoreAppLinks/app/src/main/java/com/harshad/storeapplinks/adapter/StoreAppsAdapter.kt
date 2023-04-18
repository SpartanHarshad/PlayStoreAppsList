package com.harshad.storeapplinks.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshad.storeapplinks.R
import com.harshad.storeapplinks.databinding.LayoutStoreAppItemBinding
import com.harshad.storeapplinks.repository.remote.model.SubCategory
import kotlinx.coroutines.NonDisposableHandle.parent

class StoreAppsAdapter(
    val appsList: List<SubCategory>,
    val ctx: Context,
    val clickLister: OnItemClick
) : RecyclerView.Adapter<StoreAppsAdapter.StoreViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoreAppsAdapter.StoreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemStoreAppItem: LayoutStoreAppItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.layout_store_app_item, parent, false)
        return StoreViewHolder(itemStoreAppItem, clickLister, ctx)
    }

    override fun onBindViewHolder(holder: StoreAppsAdapter.StoreViewHolder, position: Int) {
        holder.onBind(appsList[position])
    }

    override fun getItemCount(): Int {
        return appsList.size ?: 0
    }


    inner class StoreViewHolder(
        val itemViewLayout: LayoutStoreAppItemBinding,
        val clickLister: OnItemClick,
        val ctx: Context
    ) : RecyclerView.ViewHolder(itemViewLayout.root) {

        fun onBind(appDetails: SubCategory?) {
            val defaultImg =
                "https://cdn.vectorstock.com/i/1000x1000/35/52/placeholder-rgb-color-icon-vector-32173552.webp"
            itemViewLayout.tvAppName.text = appDetails?.name ?: ""
            itemViewLayout.tvInstallRange.text = appDetails?.installedRange ?: "0"
            Glide.with(ctx).load(appDetails?.icon ?: defaultImg).into(itemViewLayout.imgBanner)
            setRatingStar(appDetails?.star)
            itemViewLayout.btnDownload.setOnClickListener {
                clickLister.onButtonClick(appDetails?.appLink)
            }
        }

        private fun setRatingStar(star: String?) {
            if (star.isNullOrEmpty()) {
                val str = star?.toInt() ?: 0
                var view: View
                val layoutInflater = LayoutInflater.from(ctx)
                for (i in 0 until str) {
                    view = layoutInflater.inflate(R.layout.layout_star, null)
                    val imgView: ImageView = view.findViewById(R.id.img_full_star)
                    val viewGroup: ViewGroup = itemViewLayout.llRatingStars
                        viewGroup.addView(imgView)
                }
            }
        }
    }

}