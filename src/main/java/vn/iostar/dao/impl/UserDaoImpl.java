package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.configs.DBConnectSQL;
import vn.iostar.dao.IUserDao;
import vn.iostar.models.UserModel;

public class UserDaoImpl extends DBConnectSQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		// TODO Auto-generated method stub

		String sql = "select * from users";
		List<UserModel> list = new ArrayList<>();
		try {
			conn = super.getConnection();// ket noi database
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next() /* next tung dong toi cuoi bang */) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("images"), rs.getString("fullname")));
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findById(int Id) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM users WHERE id = " + Id + "";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				UserModel user = new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("images"), rs.getString("fullname"));
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO users(id, username, password, images, fullname) VALUES (?,?,?,?,?)";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);// nem cau sql vao cho thuc thi

			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getImages());
			ps.setString(5, user.getFullname());

			int ra = ps.executeUpdate();

			if (ra > 0)
				System.out.println("Them thanh cong");
			else
				System.out.println("Them that bai");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();

		/*
		 * List<UserModel> list = userDao.findAll(); for (UserModel user : list)
		 * System.out.println(user);
		 */

		UserModel user = new UserModel(4, "mpl1", "123", null, "Le Minh Ph");
		// userDao.insert(user);

		UserModel user1 = userDao.findById(1);
	    if (user1 != null) {
	        System.out.println(user1);
	    } else {
	        System.out.println("User not found");
	    }
	}
}
