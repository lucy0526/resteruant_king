package entity;
/**
 * 封装分页条件
 * @author asus
 *
 */
public class Condition {
	private int foodType_id;
	private String foodName;
	public int getFoodType_id() {
		return foodType_id;
	}
	public void setFoodType_id(int foodTypeId) {
		foodType_id = foodTypeId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
}
