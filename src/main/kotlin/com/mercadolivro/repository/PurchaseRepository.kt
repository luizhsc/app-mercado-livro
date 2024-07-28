package com.mercadolivro.repository

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<PurchaseModel, Int> {

    fun findPurchaseModelsByCustomer(customer: CustomerModel): List<PurchaseModel>

}
