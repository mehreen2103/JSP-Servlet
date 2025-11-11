package com.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.bean.PaymentBean;
import com.rays.util.JDBCDataSource;

public class PaymentModel {

	/* <---------Generate next primary key----------> */
	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_payment");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
			System.out.println("max id: " + pk);
		}

		conn.close();
		return pk + 1;

	}

	/* <---------insert a record----------> */
	public void add(PaymentBean bean) throws Exception {

		
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_payment values(?, ?, ?, ?, ?, ?)");

		int pk = nextPk();

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getAccount());
		pstmt.setString(4, bean.getTotal_amount());
		pstmt.setString(5, bean.getUpi_id());
		pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));

		int i = pstmt.executeUpdate();

		System.out.println("data inserted successfully: " + i);
		conn.close();
	}

	/* <---------delete a record----------> */
	public void delete(int id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_payment where id = ?");

		pstmt.setInt(1, id);

		int i = pstmt.executeUpdate();
		System.out.println("data deleted successfully: " + i);
		conn.close();

	}

	/* <---------update a record----------> */
	public void update(PaymentBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update st_payment set name = ?, account = ?, upi_id = ?, total_amount = ?, dob = ? where id = ?");

		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getAccount());
		pstmt.setString(3, bean.getUpi_id());
		pstmt.setString(4, bean.getTotal_amount());
		pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setInt(6, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("data updated successfully: " + i);
		conn.close();

	}

	/* <---------record find by login----------> */
	public PaymentBean findByLogin(String login) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_paymwnt where login = ?");

		pstmt.setString(1, login);

		ResultSet rs = pstmt.executeQuery();

		PaymentBean bean = null;
		while (rs.next()) {
			bean = new PaymentBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setAccount(rs.getString(3));
			bean.setUpi_id(rs.getString(4));
			bean.setTotal_amount(rs.getString(5));
			bean.setDob(rs.getDate(6));

		}

		conn.close();
		return bean;

	}


///*------------------------Find by Id ------------------------*///
	public PaymentBean findById(int id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_payment where id = ?");

		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		PaymentBean bean = null;
		while (rs.next()) {
			bean = new 	PaymentBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setAccount(rs.getString(3));
			bean.setUpi_id(rs.getString(4));
			bean.setTotal_amount(rs.getString(5));
			bean.setDob(rs.getDate(6));

		}

		conn.close();
		return bean;

	}
	
	/*--------------Search Method---------------*/

	public List search(PaymentBean bean, int pageNo, int pageSize) throws Exception {

		List list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_payment where 1 = 1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and firstName like '" + bean.getName() + "%'");
			}
			if (bean.getAccount() != null && bean.getAccount().length() > 0) {
				sql.append(" and lastName like '" + bean.getAccount() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				Date dob = new Date(bean.getDob().getTime());
				sql.append(" and dob like '" + dob + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		Connection conn = JDBCDataSource.getConnection();
		System.out.println("sql ----> " + sql.toString());
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			bean = new PaymentBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setAccount(rs.getString(3));
			bean.setUpi_id(rs.getString(4));
			bean.setTotal_amount(rs.getString(5));
			bean.setDob(rs.getDate(6));
			list.add(bean);

		}

		JDBCDataSource.closeConnection(conn);
		return list;

	}
}
