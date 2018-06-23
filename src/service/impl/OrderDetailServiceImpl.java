package service.impl;

import java.util.List;

import dao.IOrderDetailDao;

import entity.OrderDetail;
import factory.BeanFactory;
import service.IOrderDetailService;

public class OrderDetailServiceImpl implements IOrderDetailService {
	private IOrderDetailDao orderDetailDao = BeanFactory.getInstance("OrderDetailDao", IOrderDetailDao.class);
	public List<OrderDetail> list() {
		return orderDetailDao.list();
	}
	public List<OrderDetail> getBy_order_id(int orderId) {
		return orderDetailDao.getBy_order_id(orderId);
	}
	public void add(OrderDetail orderDetail){
		orderDetailDao.add(orderDetail);
	}
}
