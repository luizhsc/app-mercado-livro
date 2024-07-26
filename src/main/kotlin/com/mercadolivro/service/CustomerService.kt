package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?, pageable: Pageable): Page<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContains(it, pageable)
        }
        return customerRepository.findAll(pageable)
    }

    fun getById(id: Int): CustomerModel {
        return customerRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML201.code, Errors.ML201.message.format(id)) }
    }

    fun creatCustomer(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw NotFoundException(Errors.ML201.code, Errors.ML201.message.format(customer.id))
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = getById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }

}