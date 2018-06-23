package service;

import java.util.List;

import entity.FoodType;

/**
 * 菜系模块业务逻辑层接口设计，与Servlet打交道
 * 方法与dao一致
 * @author asus
 *
 */
public interface IFoodTypeService {
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
