package dao;

import java.util.Date;
import java.util.List;

import entity.Food;
import entity.Order;
import entity.OrderCondition;
import entity.PageBean;

public interface IOrderDao {
	List<Order> getOrders();
	void getLimitOrders(PageBean<Order> pb);
	void selectOrders(PageBean<Order> pb, OrderCondition orderCondition);
	int getTotalCount(PageBean<Order> pb);
	Order getOrderBy_id(int id);
	Order getOrderBy_dinnerTable_id(int dinnerTable_id);
	Order getOrderBy_orderDate(Date date);
	void update(Order order);
	
	List<Order> getOrdersBy_ordeStatus(int orderStatus);
	void add(Order order);
	void delete(int id);
}
