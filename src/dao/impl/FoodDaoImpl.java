package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.JdbcUtils;
import dao.IFoodDao;
import entity.Condition;
import entity.Food;
import entity.PageBean;

public class FoodDaoImpl implements IFoodDao {
	public void add(Food food) {
		String sql = "insert into food (foodName, foodType_id, price, m_price, introduce, img) values (?,?,?,?,?,?)";
		try{
			JdbcUtils.getQueryRunner().update(sql, food.getFoodName(), food.getFoodType_id(), food.getPrice(), food.getM_price(), food.getIntroduce(), food.getImg());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void delete(int id) {
		String sql = "delete from food where id=?";
		try{
			JdbcUtils.getQueryRunner().update(sql, id);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Food> getFoods() {
		String sql = "select * from food";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Food> getFoods(String foodName) {
		String sql = "select * from food where foodName like ?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class), "%"+foodName+"%");
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(Food food) {
		String sql = "update food set foodName=?, foodType_id=?, price=?, m_price=?, introduce=?, img=? where id=?";
		try{
			JdbcUtils.getQueryRunner().update(sql, food.getFoodName(), food.getFoodType_id(), food.getPrice(), food.getM_price(), food.getIntroduce(), food.getImg(), food.getId());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Food getFood(int id) {
		String sql = "select * from food where id=?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Food>(Food.class), id);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Food getFoodByFoodType_id(int foodTypeId) {
		String sql = "select * from food where foodType_id=?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Food>(Food.class), foodTypeId);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��ҳ��ѯ
	 */
	public void getAll(PageBean<Food> pb) {
		//��ȡ��������
		Condition condition = pb.getCondition();
		//����1����ϵ��id
		int foodType_id = condition.getFoodType_id();
		//����2����Ʒ������
		String foodName = condition.getFoodName();
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("		f.id,");
		sb.append("		f.foodName,");
		sb.append("		f.price,");
		sb.append("		f.m_price,");
		sb.append("		f.introduce,");
		sb.append("		f.img,");
		sb.append("		f.foodType_id,");
		sb.append("		t.typeName");
		sb.append(" from");
		sb.append("		food f,");
		sb.append("		foodType t");
		sb.append(" where 1=1");
		sb.append("		and f.foodType_id=t.id");
		
		//�ü��ϴ洢������ֵ����SQL��䣬 ����ֵ�ĸ�����ȷ��
		List<Object> list = new ArrayList<Object>();
		
		/*
		 * ƴ�Ӳ�ѯ����
		 */
		if(foodName != null && !"".equals(foodName.trim())){
			sb.append(" and f.foodName like ?");
			list.add("%"+foodName+"%");
		}
		else if(foodType_id > 0){
			sb.append("	and foodType_id=?");
			list.add(foodType_id);
		}
		/*
		 * ��ҳ������
		 * ��ǰҳС��1������ ��ǰҳΪ1���봫�ݵĲ�����ͬ����
		 * ��ǰҳ������ҳ�������õ�ǰҳΪ��ҳ����
		 */
		sb.append("	limit ?, ?");
		
		//�ж���תҳ������
		int totalCount = getTotalCount(pb);//�õ��ܼ�¼��
		pb.setTotalCount(totalCount);
		
		if(pb.getCurrentPage()<1){
			pb.setCurrentPage(1);
		}
		else if(pb.getCurrentPage()>pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());//Ҫ�õ���ҳ������Ҫ�õ��ܼ�¼��
		}
		int index = (pb.getCurrentPage() - 1) * pb.getPageCount();
		int count = pb.getPageCount();
		if(index<0)
			index=0;
		list.add(index);//��ʼ��
		list.add(count);//��ѯ���ص�����
		
		try{
			/*
			 * �Ѳ�ѯ�������ݷ�װ��page������
			 */
			List<Food> pageData =  JdbcUtils.getQueryRunner().query(
					sb.toString(), 
					new BeanListHandler<Food>(Food.class), 
					list.toArray()
					);
			pb.setPageData(pageData);
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getTotalCount(PageBean<Food> pb) {
		//��ȡ��������
		Condition condition = pb.getCondition();
		//����1����ϵ��id
		int foodType_id = condition.getFoodType_id();
		//����2����Ʒ������
		String foodName = condition.getFoodName();
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("		count(*)");
		sb.append(" from");
		sb.append("		food f,");
		sb.append("		foodType t");
		sb.append(" where 1=1");
		sb.append("		and f.foodType_id=t.id");
		
		//�ü��ϴ洢������ֵ����SQL��䣬 ����ֵ�ĸ�����ȷ��
		List<Object> list = new ArrayList<Object>();
		
		/*
		 * ƴ�Ӳ�ѯ����
		 */
		if(foodType_id > 0){
			sb.append("	and foodType_id=?");
			list.add(foodType_id);
		}
		if(foodName != null && !"".equals(foodName.trim())){
			sb.append(" and f.foodName like ?");
			list.add(foodName);
		}
		//��ѯ
		try {
			Long num = JdbcUtils.getQueryRunner().query(
					sb.toString(), 
					new ScalarHandler<Long>(),
					list.toArray()
			);
			
			return num.intValue()+1;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Food findBy_Id(int id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("		f.id,");
		sb.append("		f.foodName,");
		sb.append("		f.price,");
		sb.append("		f.m_price,");
		sb.append("		f.introduce,");
		sb.append("		f.img,");
		sb.append("		f.foodType_id,");
		sb.append("		t.typeName");
		sb.append(" from");
		sb.append("		food f,");
		sb.append("		foodType t");
		sb.append("	where 1=1");
		sb.append("		and f.foodType_id=t.id");
		
		try{
			return JdbcUtils.getQueryRunner().query(sb.toString(), new BeanHandler<Food>(Food.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
