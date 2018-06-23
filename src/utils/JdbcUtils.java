package utils;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 封装数据库常用操作
 * @author asus
 *
 */
public class JdbcUtils {
	/**
	 * 初始化连接池
	 */
	private static DataSource dataSource;
	static{
		dataSource = new ComboPooledDataSource();
	}
	public static DataSource getDataSource(){
		return dataSource;
	}
	/**
	 * 创建DbUtils常用工具类对象
	 */
	public static QueryRunner getQueryRunner(){
		return new QueryRunner(dataSource);
	}
}
