package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.RoleModule;
import util.DBUtil;

public class RoleModuleDao {
	public List<RoleModule> findAll() {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select ri.role_id,ri.name name_2,rmi.module_id,ri.authorization,rmi.name name_1 from "
						+"(select rm.role_id,rm.module_id,mi.name from role_module rm right outer join module_info mi "
						+"on rm.module_id = mi.module_id) rmi right outer join role_info ri "
						+"on rmi.role_id=ri.role_id";
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			List<RoleModule> list = new ArrayList<RoleModule>();
			while(rs.next()) {
				RoleModule rm = new RoleModule();
				rm.setRoleId(rs.getInt("role_id"));
				rm.setName_2(rs.getString("name_2"));
				rm.setModuleId(rs.getInt("module_id"));
				rm.setName_1(rs.getString("name_1"));
				rm.setAuthorization(rs.getString("authorization"));
				list.add(rm);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("²éÑ¯½ÇÉ«Ê§°Ü",e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	public static void main(String[] args) {
		RoleModuleDao dao = new RoleModuleDao();
		List<RoleModule> list = dao.findAll();
		for(RoleModule rm : list) {
			System.out.println(rm.getName_2());
		}
	}


	public RoleModule findById(int roleId) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from (select ri.role_id,ri.name name_2,ri.authorization,rmi.module_id,rmi.name name_1 from (select rm.role_id,rm.module_id,mi.name from role_module rm right outer join module_info mi "
						+"on rm.module_id = mi.module_id) rmi right outer join role_info ri "
						+"on rmi.role_id=ri.role_id) "
						+"where role_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, roleId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				RoleModule rm = new RoleModule();
				rm.setRoleId(rs.getInt("role_id"));
				rm.setName_2(rs.getString("name_2"));
				rm.setModuleId(rs.getInt("module_id"));
				rm.setName_1(rs.getString("name_1"));
				rm.setAuthorization(rs.getString("authorization"));
				return rm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("¸ù¾ÝID²éÑ¯½ÇÉ«Ê§°Ü",e);
		} finally {
			DBUtil.close(con);
		}
		return null;
	}
	
	public void updateRole(RoleModule rm) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update role_info set "
						+"name=?, "
						+"authorization=? "
						+"where role_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rm.getName_2());
			ps.setString(2, rm.getAuthorization().toString());
			ps.setInt(3, rm.getRoleId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("ÐÞ¸Ä½ÇÉ«Ê§°Ü",e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	public void addRole(RoleModule rm) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into role_info "
						+"values(role_seq.nextval,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rm.getName_2());
			ps.setString(2, rm.getAuthorization());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Ôö¼Ó½ÇÉ«Ê§°Ü",e);
		} finally {
			DBUtil.close(con);
		}
	}


	public void deleteRole(RoleModule rm) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "delete role_info " + "where role_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rm.getRoleId());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("É¾³ý½ÇÉ«Ê§°Ü",e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	
}
