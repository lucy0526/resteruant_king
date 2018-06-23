package factory;

import java.util.ResourceBundle;

/**
 * �򵥹����ࣺ
 * ����dao��serviceʵ��
 * @author asus
 *
 */
public class BeanFactory {
	//���������ļ�
	private static ResourceBundle bundle; 
	static{
		bundle = ResourceBundle.getBundle("instance");//�ҵ������ļ�
	}
	/**
	 * 1������ָ����key������ȫ��
	 * 2����������
	 * @return
	 */
	public static <T>T getInstance(String key, Class<T> clazz){
		String className = bundle.getString(key);
		try {
			return (T)Class.forName(className).newInstance();//�������ȫ������ʵ��
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
