package com.order.book.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_BOOK_TABLE")
public class Order {
	@Id
	@GeneratedValue
	private long id;
	private double price;
	private char side;
	private long size;
	private Date createdDate;
	private Date modifiedDate;

	public Order() {
		super();
	}
	
	
	public Order(long id, double price, char side, long size) {
		this.id = id;
		this.price = price;
		this.side = side;
		this.size = size;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public char getSide() {
		return side;
	}
	public void setSide(char side) {
		this.side = side;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
