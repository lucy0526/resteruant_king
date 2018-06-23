package entity;

public class OrderDetail {
	private int id;
	private int dinnerOrder_id;
	private int food_id;
	private int foodCount;
	private String food_name;
	private double food_price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDinnerOrder_id() {
		return dinnerOrder_id;
	}
	public void setDinnerOrder_id(int dinnerOrderId) {
		dinnerOrder_id = dinnerOrderId;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int foodId) {
		food_id = foodId;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String foodName) {
		food_name = foodName;
	}
	public double getFood_price() {
		return food_price;
	}
	public void setFood_price(double foodPrice) {
		food_price = foodPrice;
	}
	@Override
	public String toString() {
		return "OrderDetail [dinnerOrder_id=" + dinnerOrder_id + ", foodCount="
				+ foodCount + ", food_id=" + food_id + ", food_name="
				+ food_name + ", food_price=" + food_price + ", id=" + id + "]";
	}
	
}
