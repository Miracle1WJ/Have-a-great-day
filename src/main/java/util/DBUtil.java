package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {
	
	//dbcp连接池
	private  static BasicDataSource bds;
	
	//连接池只需要实例化一次；
	//连接池需要的参数只需读取一次；
	static {
		Properties p = new Properties();
		try {
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			//1.连接池必需要4个连接参数
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String pwd = p.getProperty("pwd");
			//2.连接池自身也有一些参数(有默认值)
			String initSize = p.getProperty("initSize");
			String maxSize = p.getProperty("maxSize");
			//3.创建连接池并设置连接参数
			bds = new BasicDataSource();
			//连接池会自动加载驱动，所以这里不必再Class.forName()
			bds.setDriverClassName(driver);
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(pwd);
			bds.setInitialSize(Integer.parseInt(initSize));
			bds.setMaxActive(Integer.parseInt(maxSize));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("加载资源文件失败",e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return bds.getConnection();
	}
	
	/**
	 * 使用连接池创建出来的连接，其close方法并不是真的关闭连接，而是将此连接归还给池。
	 * 并且会清空连接中的一切数据，状态置为空闲态。
	 */
	public static void close(Connection con) {
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭连接失败",e);
			}
		}
	}
	public static void main(String[] args) throws SQLException {
		Connection con = DBUtil.getConnection();
		System.out.println(con);
		DBUtil.close(con);
	}
}
