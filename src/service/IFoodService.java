package service;

import java.util.List;

import entity.Food;
import entity.PageBean;

public interface IFoodService {
	List<Food> getFoods();
	List<Food> getFoods(String foodName);
	void add(Food food);
	void delete(int id);
	void update(Food food);
	Food getFood(int id);
	Food getFoodByFoodType_id(int foodTypeId);
	/**
	 * ��ҳ��ѯ
	 */
	//��������ѯ���в�Ʒ,������data�����У����÷��أ�
	void getAll(PageBean<Food> pb);
	//������ͳ�Ʋ�Ʒ����
	int getTotalCount(PageBean<Food> pb);
	//��������ѯ��Ʒ�����ҵ���Ӧ	�Ĳ�ϵ����
	Food findBy_Id(int id);
}
