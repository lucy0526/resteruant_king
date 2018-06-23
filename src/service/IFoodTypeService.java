package service;

import java.util.List;

import entity.FoodType;

/**
 * ��ϵģ��ҵ���߼���ӿ���ƣ���Servlet�򽻵�
 * ������daoһ��
 * @author asus
 *
 */
public interface IFoodTypeService {
	//���
	void addFoodType(FoodType foodType);
	//����
	void updataFoodType(FoodType foodType);
	//ɾ��
	void deleteFoodType(int id);
	//��ѯȫ��
	List<FoodType> getFoodTypes();
	//�� ���ܰ�����ϵ���Ƶ����в�ϵ ��ѯ
	List<FoodType> getFoodTypes(String typeName);
	//����ϵdi��ѯ���ڸ���ҳ��Ҫʹ��
	FoodType getFoodType(int id);
}
