package service.impl;

import java.util.Date;
import java.util.List;

import service.IOrderService;
import dao.IOrderDao;
import entity.Order;
import entity.OrderCondition;
import entity.PageBean;
import factory.BeanFactory;

public class OrderServiceImpl implements IOrderService {
	private IOrderDao orderDao = BeanFactory.getInstance("OrderDao", IOrderDao.class);
	public void add(Order order) {
		 orderDao.add(order);
	}
	public void delete(int id) {
		 orderDao.delete(id);
	}
	public Order getOrderBy_dinnerTable_id(int dinnerTableId) {
		return orderDao.getOrderBy_dinnerTable_id(dinnerTableId);
	}
	public Order getOrderBy_id(int id) {
		return getOrderBy_id(id);
	}
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}
	public List<Order> getOrdersBy_ordeStatus(int orderStatus) {
		return orderDao.getOrdersBy_ordeStatus(orderStatus);
	}
	public void update(Order order) {
		orderDao.update(order);
	}
	
	public Order getOrderBy_orderDate(Date date){
		return orderDao.getOrderBy_orderDate(date);
	}
	@Override
	public void getLimitOrders(PageBean<Order> pb) {
		orderDao.getLimitOrders(pb);
		
	}
	@Override
	public void selectOrders(PageBean<Order> pb, OrderCondition orderCondition) {
		orderDao.selectOrders(pb, orderCondition);
	}
}
