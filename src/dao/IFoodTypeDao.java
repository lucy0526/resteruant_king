package dao;

import java.util.List;

import entity.FoodType;

/**
 * 菜系模块，dao接口设计，与数据库打交道的方法声明
 * @author asus
 *
 */
public interface IFoodTypeDao {
	//添加
	void addFoodType(FoodType foodType);
	//更新
	void updataFoodType(FoodType foodType);
	//删除
	void deleteFoodType(int id);
	//查询全部
	List<FoodType> getFoodTypes();
	//按 可能包含菜系名称的所有菜系 查询
	List<FoodType> getFoodTypes(String typeName);
	//按菜系di查询：在更新页面要使用
	FoodType getFoodType(int id);
}
