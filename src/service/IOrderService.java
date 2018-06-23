package service;

import java.util.Date;
import java.util.List;

import entity.Order;
import entity.OrderCondition;
import entity.PageBean;

public interface IOrderService {
	List<Order> getOrders();
	void getLimitOrders(PageBean<Order> pb);
	Order getOrderBy_id(int id);
	Order getOrderBy_dinnerTable_id(int dinnerTable_id);
	Order getOrderBy_orderDate(Date date);
	void update(Order order);
	void selectOrders(PageBean<Order> pb, OrderCondition orderCondition);
	List<Order> getOrdersBy_ordeStatus(int orderStatus);
	void add(Order order);
	void delete(int id);
}
