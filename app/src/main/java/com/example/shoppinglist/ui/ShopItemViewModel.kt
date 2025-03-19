package com.example.shoppinglist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.usecases.AddShopItemUseCase
import com.example.shoppinglist.domain.usecases.EditShopItemUseCase
import com.example.shoppinglist.domain.usecases.GetShopItemByIdUseCase

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _isShouldCloseScreen = MutableLiveData<Unit>()
    val isShouldCloseScreen: LiveData<Unit>
        get() = _isShouldCloseScreen

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemByIdUseCase(repository)

    fun getShopItem(id: Int) {
        _shopItem.value = getShopItemUseCase.getShopItemById(id)
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val isValidate = validateInput(name, count)
        if (isValidate) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)

            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val isValidate = validateInput(name, count)
        if (isValidate) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Double {
        return try {
            inputCount?.trim()?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }

    private fun validateInput(name: String, count: Double): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName(){
        _errorInputName.value = false
    }

    fun resetErrorInputCount(){
        _errorInputCount.value = false
    }

    private fun finishWork(){
        _isShouldCloseScreen.value = Unit
    }
}