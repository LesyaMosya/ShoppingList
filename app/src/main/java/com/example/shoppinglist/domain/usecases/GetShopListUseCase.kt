package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

class GetShopListUseCase(private val repository: ShopListRepository) {

    fun getShopList(): List<ShopItem> {
        return repository.getShopList()
    }
}