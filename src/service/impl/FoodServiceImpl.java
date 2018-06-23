package service.impl;

import java.util.List;

import service.IFoodService;
import service.IFoodTypeService;
import dao.IFoodDao;
import entity.Food;
import entity.PageBean;
import factory.BeanFactory;

public class FoodServiceImpl implements IFoodService {
	IFoodDao foodDao = BeanFactory.getInstance("FoodDao", IFoodDao.class);
	IFoodTypeService typeService = BeanFactory.getInstance("FoodTypeService", IFoodTypeService.class);
	public void add(Food food) {
		foodDao.add(food);
	}
	public void delete(int id) {
		foodDao.delete(id);
	}
	public List<Food> getFoods() {
		List<Food> list = foodDao.getFoods();
		for(Food food:list)
		{
			int id = food.getFoodType_id();
			String name = typeService.getFoodType(id).getTypeName();
			food.setFoodType_name(name);
		}
		return list;
	} 
	public List<Food> getFoods(String foodName) {
		return foodDao.getFoods(foodName);
	}
	public void update(Food food) {
		foodDao.update(food);
	}
	public Food getFood(int id) {
		Food food = foodDao.getFood(id);
//		String name = typeService.getFoodType(id).getTypeName();
//		food.setFoodType_name(name);
		return food;
	}
	public Food getFoodByFoodType_id(int foodTypeId) {
		return foodDao.getFoodByFoodType_id(foodTypeId);
	}
	@Override
	public Food findBy_Id(int id) {
		return foodDao.findBy_Id(id);
	}
	@Override
	public void getAll(PageBean<Food> pb) {
		foodDao.getAll(pb);
		for(Food food:pb.getPageData())
		{
			int id = food.getFoodType_id();
			String name = typeService.getFoodType(id).getTypeName();
			food.setFoodType_name(name);
		}
	}
	@Override
	public int getTotalCount(PageBean<Food> pb) {
		return foodDao.getTotalCount(pb);
	}
}
