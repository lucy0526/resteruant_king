package factory;

import java.util.ResourceBundle;

/**
 * 简单工厂类：
 * 创建dao、service实例
 * @author asus
 *
 */
public class BeanFactory {
	//加载配置文件
	private static ResourceBundle bundle; 
	static{
		bundle = ResourceBundle.getBundle("instance");//找到配置文件
	}
	/**
	 * 1、根据指定的key获得类的全名
	 * 2、创建对象
	 * @return
	 */
	public static <T>T getInstance(String key, Class<T> clazz){
		String className = bundle.getString(key);
		try {
			return (T)Class.forName(className).newInstance();//根据类的全名创建实例
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
