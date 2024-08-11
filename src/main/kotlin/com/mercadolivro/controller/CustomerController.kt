package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.CustomerReponse
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.CustomerService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(
        @PageableDefault(page = 0, size = 10) pageable: Pageable,
        @RequestParam name: String?
    ): Page<CustomerReponse> =
        customerService.getAll(name, pageable).map { it.toResponse() }

    @GetMapping("/{id}")
    @PreAuthorize("@customerService.isOwner(#id)")
    fun getCustomer(@PathVariable id: Int): CustomerReponse =
        customerService.getById(id).toResponse()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun creatCustomer(@Valid @RequestBody customer: PostCustomerRequest) =
        customerService.creatCustomer(customer.toCustomerModel())

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @Valid @RequestBody customer: PutCustomerRequest) {
        val customerPrevious = customerService.getById(id)
        customerService.update(customer.toCustomerModel(customerPrevious))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) = customerService.delete(id)

}