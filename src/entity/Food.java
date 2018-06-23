package entity;

public class Food {
	private int id;
	private String foodName;
	private int foodType_id;
	private double price;
	private double m_price;
	private String introduce;
	private String img;
	private String foodType_name;
	private int num;
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFoodType_name() {
		return foodType_name;
	}
	public void setFoodType_name(String foodTypeName) {
		foodType_name = foodTypeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodType_id() {
		return foodType_id;
	}
	public void setFoodType_id(int foodType_id) {
		this.foodType_id = foodType_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getM_price() {
		return m_price;
	}
	public void setM_price(double mPrice) {
		m_price = mPrice;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Food [foodName=" + foodName + ", foodType_id=" + foodType_id
				+ ", img=" + img + ", introduce=" + introduce + ", m_price="
				+ m_price + ", price=" + price + "]";
	}
	
}
