package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

class GetShopItemByIdUseCase(private val repository: ShopListRepository) {
    fun getShopItemById(shopItemId: Int): ShopItem {
        return repository.getShopItemById(shopItemId)
    }
}