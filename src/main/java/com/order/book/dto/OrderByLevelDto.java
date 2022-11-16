package com.order.book.dto;

public class OrderByLevelDto {
	private char side;
	private int level;
	public char getSide() {
		return side;
	}
	public void setSide(char side) {
		this.side = side;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "OrderByLevelDto [side=" + side + ", level=" + level + "]";
	}
	
	
}
