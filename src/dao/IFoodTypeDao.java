package dao;

import java.util.List;

import entity.FoodType;

/**
 * ��ϵģ�飬dao�ӿ���ƣ������ݿ�򽻵��ķ�������
 * @author asus
 *
 */
public interface IFoodTypeDao {
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
