package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

class EditShopItemUseCase(private val repository: ShopListRepository) {

    fun editShopItems(shopItem: ShopItem) {
        repository.editShopItems(shopItem)
    }
}