package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.ShopListRepository

class DeleteShopItemUseCase(private val repository: ShopListRepository) {

    fun deleteShopItem(shopItemId: Int) {
        repository.deleteShopItem(shopItemId)
    }
}