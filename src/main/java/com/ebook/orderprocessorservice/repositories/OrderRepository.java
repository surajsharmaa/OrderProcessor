package com.ebook.orderprocessorservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ebook.orderprocessorservice.entities.OrderTable;


public interface OrderRepository extends CrudRepository<OrderTable, Integer> {

}
