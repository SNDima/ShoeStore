package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ApplicationViewModel : ViewModel() {

    private lateinit var shoeList: MutableList<Shoe>

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    private val _eventShoeSave = MutableLiveData<Boolean>()
    val eventShoeSave: LiveData<Boolean>
        get() = _eventShoeSave

    private val _eventShoeCancel = MutableLiveData<Boolean>()
    val eventShoeCancel: LiveData<Boolean>
        get() = _eventShoeCancel

    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    private val _eventOnboardingFinish = MutableLiveData<Boolean>()
    val eventOnboardingFinish: LiveData<Boolean>
        get() = _eventOnboardingFinish

    init {
        loadShoes()
    }

    private fun loadShoes() {
        shoeList = mutableListOf(
            Shoe(
                "GEL-Kayano 28", 11.5, "ASICS",
                "For a balanced ride on a stylish design, try the ASICS® GEL-Kayano® 28."
            ),
            Shoe(
                "Butte Mono", 12.0, "UGG",
                "Complete your winter looks with the timeless design of the durably crafted UGG® Butte Mono boot."
            ),
            Shoe(
                "Juvie Linerless (Little Kid/Big Kid)", 2.0, "Vans",
                "Get the speed, convenience, and precision they need to rock the park with the Vans® Juvie Linerless snowboard boots!"
            ),
            Shoe(
                "Revolution 5", 8.0, "Nike",
                "Find your way along the track with the help of the Nike® Revolution 5 running shoes."
            ),
            Shoe(
                "Fresh Foam 880v11", 9.5, "New Balance",
                "Discover a lightweight, ultra-cushioned ride in the New Balance® Fresh Foam 880v11."
            ),
            Shoe(
                "Wellman Low Waterproof", 10.5, "Clarks",
                "A comfort take on an office shoe, the Clarks® Wellman Low Waterproof provides support all day."
            ),
            Shoe(
                "Chuck Taylor® All Star® Core Ox", 13.5, "Converse",
                "The original basketball sneakers are now defined as a stylish modern-day fashion staple! The Converse® Chuck Taylor® All Star® Core Ox sneakers are a great complement to any casual ensemble."
            ),
            Shoe(
                "Day One Safety Sublite", 12.5, "Reebok",
                ""
            ),
            Shoe(
                "Sawhorse 6\" Composite Safety Toe", 7.0, "Timberland",
                "The Timberland PRO® Sawhorse 6\" Composite Safety Toe boot has the ideal combination of support, protection, and slip resistance to see you through difficult work days in total comfort."
            ),
            Shoe(
                "Classic Clog", 11.0, "Crocs",
                "This durable, non-skid, multi- purpose clog is popular to water-sports enthusiasts, hikers, medical professionals, food service workers and fashion consumers."
            )
        )
        _shoes.value = shoeList
    }

    fun onSave(newShoe: Shoe) {
        shoeList.add(0, newShoe)
        _shoes.value = shoeList
        _eventShoeSave.value = true
    }

    fun onShoeSaveComplete() {
        _eventShoeSave.value = false
    }

    fun onCancel() {
        _eventShoeCancel.value = true
    }

    fun onShoeCancelComplete() {
        _eventShoeCancel.value = false
    }

    fun onLogin() {
        _eventLogin.value = true
    }

    fun onSignUp() {
        _eventLogin.value = true
    }

    fun onLogout() {
        _eventLogin.value = false
    }

    fun onOnboardingFinish() {
        _eventOnboardingFinish.value = true
    }

}