package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

class AddShopItemUseCase(private val repository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem) {
        repository.addShopItem(shopItem)
    }
}