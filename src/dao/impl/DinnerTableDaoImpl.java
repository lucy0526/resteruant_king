package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.IDinnerTableDao;
import entity.DinnerTable;
import entity.TableStatus;

public class DinnerTableDaoImpl implements IDinnerTableDao {
	public void add(DinnerTable table) {
		String sql = "insert into dinnerTable (tableName, tableStatus, orderDate) values (?, ?, ?)";
		try{
			JdbcUtils.getQueryRunner().update(sql, table.getTableName(), table.getTableStatus(), table.getOrderDate());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void delete(int id) {
		String sql = "delete from dinnerTable where id=?";
		try{
			JdbcUtils.getQueryRunner().update(sql, id);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<DinnerTable> getTables() {
		String sql = "select * from dinnerTable";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(DinnerTable table) {
		String sql = "update dinnerTable set tableStatus=? , orderDate=?  where id=?";
		try{
			JdbcUtils.getQueryRunner().update(sql, table.getTableStatus(), table.getOrderDate(), table.getId());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<DinnerTable> getTables(String tableName) {
		String sql = "select * from dinnerTable where tableName like ?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), "%"+tableName+"%");
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public DinnerTable getTableBy_id(int id) {
		String sql = "select * from dinnerTable where id=?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<DinnerTable> getTableBy_TableStatus(TableStatus ts) {
		String sql = "select * from dinnerTable where tableStatus=?";
		/*int status = -1;
		
		 * еп╤о
		 
		if(ts == TableStatus.Free){
			status = 0;
		}
		else{
			status = 1;
		}*/
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), ts.ordinal());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
