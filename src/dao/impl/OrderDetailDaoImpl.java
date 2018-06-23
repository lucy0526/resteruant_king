package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.IOrderDetailDao;
import entity.OrderDetail;

public class OrderDetailDaoImpl implements IOrderDetailDao {
	public List<OrderDetail> list() {
		String sql = "select * from dinnerOrderDetail";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<OrderDetail> getBy_order_id(int orderId) {
		String sql = "select * from dinnerOrderDetail where dinnerOrder_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class), orderId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void add(OrderDetail orderDetail){
		String sql = "INSERT INTO dinnerorderdetail (dinnerOrder_id, food_id, foodCount) VALUES (?, ?, ?)";
		try {
			JdbcUtils.getQueryRunner().update(sql, orderDetail.getDinnerOrder_id(), orderDetail.getFood_id(), orderDetail.getFoodCount());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
