package entity;

import java.util.Date;

public class Order {
	private int id;
	private int dinnerTable_id;
	private Date orderDate;
	private double totalPrice;
	private int orderStatus;
	private String dinnerTable_name;
	
	public String getDinnerTable_name() {
		return dinnerTable_name;
	}
	public void setDinnerTable_name(String dinnerTableName) {
		dinnerTable_name = dinnerTableName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDinnerTable_id() {
		return dinnerTable_id;
	}
	public void setDinnerTable_id(int dinnerTableId) {
		dinnerTable_id = dinnerTableId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "Order [dinnerTable_id=" + dinnerTable_id + ", id=" + id
				+ ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", totalPrice=" + totalPrice + "]";
	}  
	
}
