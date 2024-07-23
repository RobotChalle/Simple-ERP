package dto;

import java.sql.Date;

public class OrderDTO {
	private int orderNum;
	private String tradCorpName;
	private String orderDate;
	private String[] productName = new String[5];
	private int[] quantity = new int [5];
	private int[] Price = new int [5];
	private int[] totalPrice = new int [5];
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getTradCorpName() {
		return tradCorpName;
	}
	public void setTradCorpName(String tradCorpName) {
		this.tradCorpName = tradCorpName;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getProductName(int i) {
		return productName[i];
	}
	public void setProductName(int i,String productName) {
		this.productName[i] = productName;
	}
	public int getQuantity(int i) {
		return quantity[i];
	}
	public void setQuantity(int i , int quantity) {
		this.quantity[i] = quantity;
	}
	public int getPrice(int i) {
		return Price[i];
	}
	public void setPrice(int i , int Price) {
		this.Price[i] = Price;
	}
	public int getTotalPrice(int i) {
		return totalPrice[i];
	}
	public void setTotalPrice(int i , int orderPrice) {
		this.totalPrice[i] = orderPrice;
	}
}