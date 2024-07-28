package com.mercadolivro.service

import com.mercadolivro.controller.response.PurchaseResponse
import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val customerService: CustomerService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel) {
        val purchase = purchaseRepository.save(purchaseModel)
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }

    fun getByIdCustomer(id: Int): List<PurchaseModel> {
        val customer = customerService.getById(id)
        return purchaseRepository.findPurchaseModelsByCustomer(customer)
    }

}
