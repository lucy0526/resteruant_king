package dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import service.impl.DinnerTableServiceImpl;
import utils.JdbcUtils;
import dao.IOrderDao;
import entity.DinnerTable;
import entity.Food;
import entity.Order;
import entity.OrderCondition;
import entity.PageBean;

public class OrderDaoImpl implements IOrderDao {
	public List<Order> getOrders() {
		String sql = "select * from dinnerOrder";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Order>(Order.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(Order order) {
		String sql = "update dinnerOrder set orderStatus=? where id=?";
		try{
			JdbcUtils.getQueryRunner().update(sql, order.getOrderStatus(), order.getId());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Order getOrderBy_dinnerTable_id(int dinnerTableId) {
		String sql = "select * from dinnerOrder where dinnerTable_id=?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Order>(Order.class), dinnerTableId);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void add(Order order) {
		String sql = "INSERT INTO dinnerorder (dinnerTable_id, orderDate, totalPrice, orderStatus) VALUES (?, ?, ?, ?);";
		try {
			JdbcUtils.getQueryRunner().update(sql, order.getDinnerTable_id(), order.getOrderDate(), order.getTotalPrice(), order.getOrderStatus());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Order getOrderBy_orderDate(Date date){
		String sql = "select * from dinnerOrder where orderDate=?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Order>(Order.class), date);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(int id) {
		
	}
	public Order getOrderBy_id(int id) {
		 
		return null;
	}
	public List<Order> getOrdersBy_ordeStatus(int orderStatus) {
		 
		return null;
	}
	@Override
	public void getLimitOrders(PageBean<Order> pb) {
		String sql = "select * from dinnerOrder ORDER BY id DESC limit ?, ?";
		int totalCount = getTotalCount(pb);
		pb.setTotalCount(totalCount);
		if(pb.getCurrentPage()<1){
			pb.setCurrentPage(1);
		}
		else if(pb.getCurrentPage()>pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());//要得到总页数，先要得到总记录数
		}
		int index = (pb.getCurrentPage() - 1) * pb.getPageCount();
		int count = pb.getPageCount();
		if(index<0)
			index=0;
		
		List<Object> list = new ArrayList<Object>();
		list.add(index);
		list.add(count);
		try{
			List<Order> pageData =  JdbcUtils.getQueryRunner().query(
					sql,
					new BeanListHandler<Order>(Order.class), 
					list.toArray()
					);
			pb.setPageData(pageData);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void selectOrders(PageBean<Order> pb, OrderCondition orderCondition) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("		*");
		sb.append(" from");
		sb.append("		dinnerOrder ");
		sb.append(" where 1=1");
		
		String tableName = orderCondition.getDinnerTable_name(); 
		if(tableName.trim()!="")
		{
			int id = 0;
			DinnerTableServiceImpl dinnerTableService = new DinnerTableServiceImpl();
			List<DinnerTable> dinnerTables = dinnerTableService.getTables(tableName);
			if(dinnerTables.size()>=1){
				id = dinnerTables.get(0).getId();
				sb.append(" and dinnerTable_id="+id);
				for(int i=1; i<dinnerTables.size(); i++){
					id = dinnerTables.get(i).getId();
					sb.append(" or dinnerTable_id="+id);
				}
			}
			else if(id==0){
				sb.append(" and dinnerTable_id="+id);
			}
		}
		
		int id = orderCondition.getId();
		if(id != 0)
			sb.append(" and id="+id);
		double mintotalPrice = orderCondition.getMintotalPrice();
		double maxtotalPrice = orderCondition.getMaxtotalPrice();
		if(mintotalPrice != 0)
			sb.append(" and totalPrice>="+mintotalPrice);
		if(maxtotalPrice != 0)
			sb.append(" and totalPrice<="+maxtotalPrice);
		
		sb.append(" ORDER BY id DESC	limit ?, ?");
		int totalCount = getSelCount(pb, orderCondition);
		
		pb.setTotalCount(totalCount);
		
		if(pb.getCurrentPage()<1){
			pb.setCurrentPage(1);
		}
		else if(pb.getCurrentPage()>pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());//要得到总页数，先要得到总记录数
		}
		int index = (pb.getCurrentPage() - 1) * pb.getPageCount();
		int count = pb.getPageCount();
		if(index<0)
			index=0;
		
		List<Object> list = new ArrayList<Object>();
		list.add(index);
		list.add(count);
		try{
			List<Order> pageData =  JdbcUtils.getQueryRunner().query(
					sb.toString(),
					new BeanListHandler<Order>(Order.class), 
					list.toArray()
					);
			pb.setPageData(pageData);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public int getTotalCount(PageBean<Order> pb) {
		String sql = "select count(*) from dinnerOrder";
		try{
			Long num = JdbcUtils.getQueryRunner().query(
					sql, 
					new ScalarHandler<Long>()
			);
			return num.intValue();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public int getSelCount(PageBean<Order> pb, OrderCondition orderCondition) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("		count(*)");
		sb.append(" from");
		sb.append("		dinnerOrder ");
		sb.append(" where 1=1");
		
		String tableName = orderCondition.getDinnerTable_name(); 
		if(tableName.trim()!="")
		{
			int id = 0;
			DinnerTableServiceImpl dinnerTableService = new DinnerTableServiceImpl();
			List<DinnerTable> dinnerTables = dinnerTableService.getTables(tableName);
			if(dinnerTables.size()>=1){
				id = dinnerTables.get(0).getId();
				sb.append(" and dinnerTable_id="+id);
				for(int i=1; i<dinnerTables.size(); i++){
					id = dinnerTables.get(i).getId();
					sb.append(" or dinnerTable_id="+id);
				}
			}
			else if(id==0){
				sb.append(" and dinnerTable_id="+id);
			}
		}
		
		int id = orderCondition.getId();
		if(id != 0)
			sb.append(" and id="+id);
		double mintotalPrice = orderCondition.getMintotalPrice();
		double maxtotalPrice = orderCondition.getMaxtotalPrice();
		if(mintotalPrice != 0)
			sb.append(" and totalPrice>="+mintotalPrice);
		if(maxtotalPrice != 0)
			sb.append(" and totalPrice<="+maxtotalPrice);
		
		try{
			Long num = JdbcUtils.getQueryRunner().query(
					sb.toString(), 
					new ScalarHandler<Long>()
			);
			return num.intValue();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
