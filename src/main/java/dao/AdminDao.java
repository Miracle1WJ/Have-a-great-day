package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import entity.Admin;
import util.DBUtil;

@Component("ad")
public class AdminDao {
	
	public List<Admin> findAll() {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from admin_info " + "order by admin_id";
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			List<Admin> list = new ArrayList<Admin>();
			while(rs.next()) {
				Admin a = createAdmin(rs);
				list.add(a);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询管理员失败",e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	/**
	 * 根据账号查询管理员
	 * @param adminCode 账号
	 * @return
	 */
	public Admin findByCode(String adminCode) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from admin_info " + "where admin_code=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, adminCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return createAdmin(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询管理员失败",e);
		} finally {
			DBUtil.close(con);
		}
		return null;
	}
	
	public Admin findById(int adminId) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from admin_info " + "where admin_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return createAdmin(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询管理员失败",e);
		} finally {
			DBUtil.close(con);
		}
		return null;
	}
	
	private Admin createAdmin(ResultSet rs) throws SQLException {
		Admin a = new Admin();
		a.setAdminId(rs.getInt("admin_id"));
		a.setAdminCode(rs.getString("admin_code"));
		a.setPassword(rs.getString("password"));
		a.setName(rs.getString("name"));
		a.setTelephone(rs.getString("telephone"));
		a.setEmail(rs.getString("email"));
		a.setEnrolldate(rs.getTimestamp("enrolldate"));
		a.setRole(rs.getString("role"));
		return a;
	}
	
	public void add(Admin admin) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into admin_info "
					+ "values(admin_seq.nextval,"
					+ "?,?,?,?,?,sysdate,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getName());
			//setInt、setDouble不允许传入null，但当前业务中，这些值的确可能为null，
			//且该字段也的确可以为null。此时，可以将这些字段当作对象（setObject）处理。
			ps.setString(2, admin.getAdminCode());
			ps.setString(3, admin.getPassword());
			ps.setString(4, admin.getTelephone());
			ps.setString(5, admin.getEmail());
			ps.setString(6, admin.getRole());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("增加管理员失败",e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	public void updateAdmin(Admin admin) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update admin_info set "
						+"name=?,"
						+"telephone=?,"
						+"email=?,"
						+"role=? "
						+"where admin_code=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getTelephone());
			ps.setString(3, admin.getEmail());
			ps.setString(4, admin.getRole());
			ps.setString(5, admin.getAdminCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("修改管理员失败", e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	public void deleteAdmin(Admin a) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "delete admin_info " + "where admin_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, a.getAdminId());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除管理员失败",e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	public void updatePwd(Admin admin,String newPwd) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update admin_info set "
						+"password=? "
						+"where admin_code=? and "
						+"password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newPwd);
			ps.setString(2, admin.getAdminCode());
			ps.setString(3, admin.getPassword());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("修改密码失败",e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	public void updateInfo(Admin admin) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update admin_info set "
						+"name=?,"
						+"telephone=?,"
						+"email=?,"
						+"enrolldate=? "
						+"where admin_code=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getTelephone());
			ps.setString(3, admin.getEmail());
			ps.setTimestamp(4, admin.getEnrolldate());
			ps.setString(5, admin.getAdminCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("修改个人信息失败", e);
		} finally {
			DBUtil.close(con);
		}
	}
	public static void main(String[] args) {
		AdminDao dao = new AdminDao();
		List<Admin> list = dao.findAll();
		for(Admin a : list) {
			System.out.println(a.getName());
		}
	}

}
