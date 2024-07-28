package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.BookReponse
import com.mercadolivro.controller.response.CustomerReponse
import com.mercadolivro.controller.response.PurchaseResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.PurchaseModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(customer: CustomerModel): CustomerModel {
    return CustomerModel(id = customer.id, name = this.name, email = this.email, customer.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(name = this.name, price = this.price, status = BookStatus.ATIVO, customer = customer)
}

fun PutBookRequest.toBookModel(book: BookModel): BookModel {
    return BookModel(
        id = book.id,
        name = this.name ?: book.name,
        price = this.price ?: book.price,
        status = book.status,
        customer = book.customer
    )
}

fun CustomerModel.toResponse(): CustomerReponse {
    return CustomerReponse(id = this.id, name = this.name, email = this.email, status = this.status)
}

fun BookModel.toResponse(): BookReponse {
    return BookReponse(id = this.id, name = this.name, price = this.price, customer = this.customer)
}

fun PurchaseModel.toResponse(): PurchaseResponse {
    return PurchaseResponse(
        idPurchase = this.id,
        idBooks = this.books.map { it.toResponse() }.map { it.id },
        nfe = this.nfe,
        price = this.price,
        createdAt = this.createdAt
    )
}
