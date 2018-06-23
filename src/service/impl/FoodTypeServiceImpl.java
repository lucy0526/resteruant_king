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
	 * ����Dao�ķ���
	 * ����Ĵ�������д��
	 * ���������ļ���д��������ר�Ŵ�������
	 * �޸������ļ��������±���
	 */
//	private IFoodTypeDao foodTypeDao = new FoodTypeDaoImpl();
	//������������
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
