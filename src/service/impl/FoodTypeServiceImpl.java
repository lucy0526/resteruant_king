package service.impl;

import java.util.List;

import service.IFoodService;
import service.IFoodTypeService;
import dao.IFoodTypeDao;
import entity.Food;
import entity.FoodType;
import factory.BeanFactory;

public class FoodTypeServiceImpl implements IFoodTypeService {
	/**
	 * 调用Dao的方法
	 * 对象的创建不能写死
	 * 利用配置文件，写个工厂类专门创建对象；
	 * 修改配置文件不用重新编译
	 */
//	private IFoodTypeDao foodTypeDao = new FoodTypeDaoImpl();
	//工厂创建对象
	private IFoodTypeDao foodTypeDao = BeanFactory.getInstance("FoodTypeDao", IFoodTypeDao.class);
//	private IFoodService foodService = BeanFactory.getInstance("FoodService", IFoodService.class);
	public void addFoodType(FoodType foodType) {
		foodTypeDao.addFoodType(foodType);
	}
	public void deleteFoodType(int id) {
			foodTypeDao.deleteFoodType(id);
		/*Food food = foodService.getFoodByFoodType_id(id);
		if(food==null)
		else
			throw new RuntimeException();*/
	}
	public FoodType getFoodType(int id) {
		return foodTypeDao.getFoodType(id);
	}
	public List<FoodType> getFoodTypes() {
		return foodTypeDao.getFoodTypes();
	}
	public List<FoodType> getFoodTypes(String typeName) {
		return foodTypeDao.getFoodTypes(typeName);
	}
	public void updataFoodType(FoodType foodType) {
		foodTypeDao.updataFoodType(foodType);
	}
}
