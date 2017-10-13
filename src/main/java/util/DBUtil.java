package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {
	
	//dbcp���ӳ�
	private  static BasicDataSource bds;
	
	//���ӳ�ֻ��Ҫʵ����һ�Σ�
	//���ӳ���Ҫ�Ĳ���ֻ���ȡһ�Σ�
	static {
		Properties p = new Properties();
		try {
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			//1.���ӳر���Ҫ4�����Ӳ���
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String pwd = p.getProperty("pwd");
			//2.���ӳ�����Ҳ��һЩ����(��Ĭ��ֵ)
			String initSize = p.getProperty("initSize");
			String maxSize = p.getProperty("maxSize");
			//3.�������ӳز��������Ӳ���
			bds = new BasicDataSource();
			//���ӳػ��Զ������������������ﲻ����Class.forName()
			bds.setDriverClassName(driver);
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(pwd);
			bds.setInitialSize(Integer.parseInt(initSize));
			bds.setMaxActive(Integer.parseInt(maxSize));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("������Դ�ļ�ʧ��",e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return bds.getConnection();
	}
	
	/**
	 * ʹ�����ӳش������������ӣ���close������������Ĺر����ӣ����ǽ������ӹ黹���ء�
	 * ���һ���������е�һ�����ݣ�״̬��Ϊ����̬��
	 */
	public static void close(Connection con) {
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("�ر�����ʧ��",e);
			}
		}
	}
	public static void main(String[] args) throws SQLException {
		Connection con = DBUtil.getConnection();
		System.out.println(con);
		DBUtil.close(con);
	}
}
