package com.order.book.usecase;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.order.book.dto.OrderByLevelDto;
import com.order.book.dto.ResponseInfo;
import com.order.book.entity.Order;
import com.order.book.repository.OrderRepository;

@Service
public class OrderBook {
	private final String DONE = "done";
	private final String FAILED = "failed";
	private final String ERROR = "error";
	private final String SIDE_MESSAGE = "Side Must be B or O";
	private final String LEVEL_MESSAGE = "Level cannot below 1";
	
	@Autowired
	OrderRepository orderRepository;
	
	public String insertOrder(Order order) {
		try {
			order.setCreatedDate(new Date());
			orderRepository.save(order);
			return DONE;
		} catch (Exception e) {
			e.printStackTrace();
			return "failed to insert: " +e.getLocalizedMessage();
		}
	}
	
	public String deleteOrder(long id) {
		try {
			orderRepository.deleteById(id);
			return DONE;
		} catch (EmptyResultDataAccessException e) {
			return "No Data with Id: " + id;
		} catch (Exception e) {
			e.printStackTrace();
			return "failed to delete: " +e.getLocalizedMessage();
		}
	}
	
	public String updateSize(Order request) {
		try {
			Order entity = orderRepository.getReferenceById(request.getId());
			entity.setSize(request.getSize());
			entity.setModifiedDate(new Date());
			orderRepository.save(entity);
			return DONE;
		} catch(EntityNotFoundException e) {
			return "No Data with Id: " + request.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return "failed to update size: " +e.getLocalizedMessage();
		}
	}
	
	public ResponseInfo bestPriceByLevel(OrderByLevelDto dto){
		List<Double> listPrice;
		Double priceByLevel;
		
		try {
			if(dto.getLevel()<1) {
				return new ResponseInfo(FAILED, LEVEL_MESSAGE, null);
			}
			
			switch (dto.getSide()) {
			case 'B':
				listPrice = orderRepository.findBySide(dto.getSide())
											.stream()
											.sorted(Comparator.comparing(Order::getPrice).reversed())
											.map(Order::getPrice)
											.distinct()
											.collect(Collectors.toList());
				priceByLevel = listPrice.get(dto.getLevel() - 1);
				return new ResponseInfo(DONE, DONE, priceByLevel);
			case 'O':
				listPrice = orderRepository.findBySide(dto.getSide())
											.stream()
											.sorted(Comparator.comparing(Order::getPrice))
											.map(Order::getPrice)
											.distinct()
											.collect(Collectors.toList());
				priceByLevel = listPrice.get(dto.getLevel() - 1);
				return new ResponseInfo(DONE, DONE, priceByLevel);
			default:
				return new ResponseInfo(FAILED, SIDE_MESSAGE, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfo(FAILED, ERROR, e.getLocalizedMessage());
		}
	}
	
	public ResponseInfo bestTotalSizeByLevel(OrderByLevelDto dto){
		try {
			if(dto.getLevel()<1) {
				return new ResponseInfo(FAILED, LEVEL_MESSAGE, null);
			}
			
			List<Order> orderBySide = orderRepository.findBySide(dto.getSide());
			List<Double> orderBySideDictinct = orderBySide.stream()
					.sorted(Comparator.comparing(Order::getPrice))
					.map(Order::getPrice)
					.distinct()
					.collect(Collectors.toList());
			Double priceByLevel = orderBySideDictinct.get(dto.getLevel() - 1);
			
			Long longList = orderBySide.stream()
					.filter(a -> a.getPrice() == priceByLevel)
					.map(Order::getSize)
					.collect(Collectors.summingLong(Long::longValue));
			return new ResponseInfo(DONE, DONE, longList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfo(FAILED, ERROR, e.getLocalizedMessage());
		}
	}
	
	public ResponseInfo getOrdersSide(OrderByLevelDto dto) {
		try {
			List<Order> listOrder;
			switch (dto.getSide()) {
			case 'B':
				listOrder = orderRepository.findBySide(dto.getSide())
		  		.stream()
		  		.sorted(Comparator.comparing(Order::getPrice).thenComparing(Order::getCreatedDate).reversed())
		  		.collect(Collectors.toList());
				
				return new ResponseInfo(DONE, DONE, listOrder);
			case 'O':
				listOrder = orderRepository.findBySide(dto.getSide())
		  		.stream()
		  		.sorted(Comparator.comparing(Order::getPrice).reversed().thenComparing(Order::getCreatedDate).reversed())
		  		.collect(Collectors.toList());
				
				return new ResponseInfo(DONE, DONE, listOrder);
			default:
				return new ResponseInfo(FAILED, SIDE_MESSAGE, null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfo(FAILED, ERROR, e.getLocalizedMessage());
		}
			
	}
}
