package com.order.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.book.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	public List<Order> findBySide(char side);
}
