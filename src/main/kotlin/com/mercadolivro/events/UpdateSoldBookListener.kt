package com.mercadolivro.events

import com.mercadolivro.service.BookService
import com.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class UpdateSoldBookListener(private val bookService: BookService) {

    @Async
    @EventListener
    fun listener(purchaseEvent: PurchaseEvent) {
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }

}