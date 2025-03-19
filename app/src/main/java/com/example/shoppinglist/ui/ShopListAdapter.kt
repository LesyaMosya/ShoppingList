package com.example.shoppinglist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ITEM_ENABLED -> R.layout.item_shop_enabled
            ITEM_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        holder.tvName.text = shopItem.name
        holder.tvCount.text = "${shopItem.count}"
        holder.itemView.setOnLongClickListener{
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isEnabled) ITEM_ENABLED
        else ITEM_DISABLED
    }

    companion object {
        const val ITEM_ENABLED = 1
        const val ITEM_DISABLED = 0

        const val MAX_PULL_SIZE = 15
    }
}