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
			throw new RuntimeException("��ѯ����Աʧ��",e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	/**
	 * �����˺Ų�ѯ����Ա
	 * @param adminCode �˺�
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
			throw new RuntimeException("��ѯ����Աʧ��",e);
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
			throw new RuntimeException("��ѯ����Աʧ��",e);
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
			//setInt��setDouble��������null������ǰҵ���У���Щֵ��ȷ����Ϊnull��
			//�Ҹ��ֶ�Ҳ��ȷ����Ϊnull����ʱ�����Խ���Щ�ֶε�������setObject������
			ps.setString(2, admin.getAdminCode());
			ps.setString(3, admin.getPassword());
			ps.setString(4, admin.getTelephone());
			ps.setString(5, admin.getEmail());
			ps.setString(6, admin.getRole());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("���ӹ���Աʧ��",e);
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
			throw new RuntimeException("�޸Ĺ���Աʧ��", e);
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
			throw new RuntimeException("ɾ������Աʧ��",e);
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
			throw new RuntimeException("�޸�����ʧ��",e);
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
			throw new RuntimeException("�޸ĸ�����Ϣʧ��", e);
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
