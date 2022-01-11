package com.udacity.shoestore.models

import android.os.Parcelable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import com.udacity.shoestore.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(
    private var name: String,
    private var size: Double,
    private var company: String,
    private var description: String,
    val images: List<String> = mutableListOf()
) : Parcelable, Observable {
    constructor() : this("", 0.0, "", "")

    private val propertyChangeRegistry = PropertyChangeRegistry()

    @Bindable
    fun getName(): String {
        return name
    }

    fun setName(value: String) {
        if(name != value){
            name = value
            propertyChangeRegistry.notifyChange(this, BR.name)
        }
    }

    @Bindable
    fun getSize(): Double {
        return size
    }

    fun setSize(value: Double) {
        if(size != value){
            size = value
            propertyChangeRegistry.notifyChange(this, BR.size)
        }
    }

    @Bindable
    fun getCompany(): String {
        return company
    }

    fun setCompany(value: String) {
        if(company != value){
            company = value
            propertyChangeRegistry.notifyChange(this, BR.company)
        }
    }

    @Bindable
    fun getDescription(): String {
        return description
    }

    fun setDescription(value: String) {
        if(description != value){
            description = value
            propertyChangeRegistry.notifyChange(this, BR.description)
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }
}