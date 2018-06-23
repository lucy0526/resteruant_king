package entity;

import java.util.Date;

public class OrderCondition {
	private Date orderDate;
	private int  id = 0;
	private String dinnerTable_name;
	private int orderStatus;
	private double mintotalPrice = 0;
	private double maxtotalPrice = 0;
	public double getMaxtotalPrice() {
		return maxtotalPrice;
	}
	public void setMaxtotalPrice(double maxtotalPrice) {
		this.maxtotalPrice = maxtotalPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDinnerTable_name() {
		return dinnerTable_name;
	}
	public void setDinnerTable_name(String dinnerTableName) {
		dinnerTable_name = dinnerTableName;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getMintotalPrice() {
		return mintotalPrice;
	}
	public void setMintotalPrice(double mintotalPrice) {
		this.mintotalPrice = mintotalPrice;
	}
	@Override
	public String toString() {
		return "OrderCondition [dinnerTable_name=" + dinnerTable_name + ", id="
				+ id + ", maxtotalPrice=" + maxtotalPrice + ", mintotalPrice="
				+ mintotalPrice + ", orderDate=" + orderDate + ", orderStatus="
				+ orderStatus + "]";
	}
	
}
