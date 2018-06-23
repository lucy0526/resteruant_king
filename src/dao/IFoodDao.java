package dao;

import java.util.List;

import entity.Food;
import entity.PageBean;

public interface IFoodDao {
	List<Food> getFoods();
	List<Food> getFoods(String foodName);
	void add(Food food);
	void delete(int id);
	void update(Food food);
	Food getFood(int id);
	Food getFoodByFoodType_id(int foodType_id);
	/**
	 * 分页查询
	 */
	//按条件查询所有菜品,保存在data属性中，不用返回！
	void getAll(PageBean<Food> pb);
	//按条件统计菜品总数
	int getTotalCount(PageBean<Food> pb);
	//按条件查询菜品，并找到对应	的菜系名称
	Food findBy_Id(int id);
	
}
