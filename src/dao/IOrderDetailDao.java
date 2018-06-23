package dao;

import java.util.List;

import entity.OrderDetail;

public interface IOrderDetailDao {
	List<OrderDetail> list();
	List<OrderDetail> getBy_order_id(int order_id);
	void add(OrderDetail orderDetail);
}
