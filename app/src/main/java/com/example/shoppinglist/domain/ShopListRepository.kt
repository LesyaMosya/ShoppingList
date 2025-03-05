package com.example.shoppinglist.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItemId: Int)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItemById(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>
}