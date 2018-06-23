package dao.impl;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.IFoodTypeDao;
import entity.FoodType;
public class FoodTypeDaoImpl implements IFoodTypeDao{
	public void addFoodType(FoodType foodType) {
		String sql = "INSERT INTO foodType(typeName) VALUES (?);";
		try{
			JdbcUtils.getQueryRunner().update(sql, foodType.getTypeName());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void deleteFoodType(int id) {
		String sql = "delete from foodType where id=?";
		try{
			JdbcUtils.getQueryRunner().update(sql, id);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<FoodType> getFoodTypes(String typeName) {
		String sql = "select * from foodType where typeName like ?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class), "%"+typeName+"%");
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public FoodType getFoodType(int id) {
		String sql = "select * from foodType where id=?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<FoodType>(FoodType.class), id);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<FoodType> getFoodTypes() {
		String sql = "select * from foodType";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void updataFoodType(FoodType foodType) {
		String sql = "update foodType set typeName=? where id=?";
		try{
			JdbcUtils.getQueryRunner().update(sql, foodType.getTypeName(), foodType.getId());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
