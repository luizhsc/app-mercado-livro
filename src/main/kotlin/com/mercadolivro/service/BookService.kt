package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.awt.print.Book

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun create(bookModel: BookModel) {
        bookRepository.save(bookModel)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActive(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)

    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML101.code, Errors.ML101.message.format(id)) }
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.DELETADO
        update(book)
    }

    fun update(bookModel: BookModel) {
        bookRepository.save(bookModel)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        books.forEach {
            it.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

    fun findAllByIds(booksIds: List<Int>): List<BookModel> {
        return bookRepository.findAllById(booksIds)
    }

    fun purchase(books: MutableList<BookModel>) {
        books.map { it.status = BookStatus.VENDIDO }
        bookRepository.saveAll(books)
    }

}
