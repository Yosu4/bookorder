package com.order.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.book.dto.OrderByLevelDto;
import com.order.book.dto.ResponseInfo;
import com.order.book.entity.Order;
import com.order.book.usecase.OrderBook;

@RestController("/")
public class BookController {
	@Autowired
	OrderBook orderBook;
	
	@PostMapping("insert_order")
	public String insertOrder(@RequestBody Order order) {
		return orderBook.insertOrder(order);
	}
	
	@PostMapping("delete_order")
	public String deleteOrder(@RequestBody Order order) {
		return orderBook.deleteOrder(order.getId());
	}
	
	@PostMapping("update_size_order")
	public String updateOrder(@RequestBody Order order) {
		return orderBook.updateSize(order);
	}
	
	@PostMapping("get_price_by_level")
	public ResponseInfo getOrderByLevel(@RequestBody OrderByLevelDto dto) {
		return orderBook.bestPriceByLevel(dto);
	}
	
	@PostMapping("get_total_size_by_level")
	public ResponseInfo getTotalSizeByLevel(@RequestBody OrderByLevelDto dto) {
		return orderBook.bestTotalSizeByLevel(dto);
	}
	
	@PostMapping("get_all_side")
	public ResponseInfo getAllSide(@RequestBody OrderByLevelDto dto) {
		return orderBook.getOrdersSide(dto);
	}
}
