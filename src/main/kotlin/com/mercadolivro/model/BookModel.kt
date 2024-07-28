package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.BadRequestException
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
data class BookModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,

    @Column var name: String,

    @Column var price: BigDecimal,

    @ManyToOne @JoinColumn(name = "customer.id") var customer: CustomerModel?

) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (value == BookStatus.CANCELADO || value == BookStatus.DELETADO) {
                throw BadRequestException(Errors.ML102.code, Errors.ML102.message.format(value.name))
            } else if (value == BookStatus.VENDIDO) {
                throw BadRequestException(Errors.ML103.code, Errors.ML103.message)
            }
            field = value
        }

    constructor(id: Int? = null, name: String, price: BigDecimal, customer: CustomerModel?, status: BookStatus?) : this(
        id, name, price, customer
    ) {
        this.status = status
    }
}