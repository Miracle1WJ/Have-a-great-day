package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Account;
import util.DBUtil;

public class AccountDao {
	
	public List<Account> findAll() {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from account_info " + "order by account_id";
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			List<Account> list = new ArrayList<Account>();
			while(rs.next()) {
				Account a = createAccount(rs);
				list.add(a);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("≤È—Ø’ÀŒÒ ß∞‹",e);
		} finally {
			DBUtil.close(con);
		}
	}

	public void addAccount(Account a) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into account_info "
					   + "values(account_info_seq,"
					   + "?,?,'0',sysdate,null,null,"
					   + "?,?,?,?,?,?,?,?,?,?,null,?)";
			PreparedStatement ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("‘ˆº”’ÀŒÒ’À∫≈ ß∞‹",e);
		} finally {
			DBUtil.close(con);
		}
	}
	
	private Account createAccount(ResultSet rs) throws SQLException {
		Account a = new Account();
		a.setAccountId(rs.getInt("account_id"));
		a.setRecommenderId(rs.getInt("recommender_id"));
		a.setBirthdate(rs.getDate("birthdate"));
		a.setCreateDate(rs.getDate("create_date"));
		a.setEmail(rs.getString("email"));
		a.setGender(rs.getString("gender"));
		a.setIdcardNo(rs.getString("idcard_no"));
		a.setLastLoginIp(rs.getString("last_login_ip"));
		a.setLastLoginTime(rs.getTimestamp("last_login_time"));
		a.setLoginName(rs.getString("login_name"));
		a.setLoginPasswd(rs.getString("login_passwd"));
		a.setMailaddress(rs.getString("mailaddress"));
		a.setOccupation(rs.getString("occupation"));
		a.setPauseDate(rs.getDate("pause_date"));
		a.setQq(rs.getString("qq"));
		a.setRealName(rs.getString("real_name"));
		a.setStatus(rs.getString("status"));
		a.setTelephone(rs.getString("telephone"));
		a.setZipcode(rs.getString("zipcode"));
		return a;
	}
	/*
	public static void main(String[] args) {
		AccountDao dao = new AccountDao();
		List<Account> list = dao.findAll();
		for(Account a : list) {
			System.out.println(a.getCreateDate());
		}
	}
	*/

}
