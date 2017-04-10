package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Contact;

/**
 * 
 * @author AMIT
 * @class ContactManagementRepoImpl
 * 
 */
public class ContactManagementRepoImpl implements ContactManagementRepo {

	@Override
	public void addContact(final Contact contact) throws SQLException {
		try (Connection conn = getConnection()) {
			PreparedStatement stmt = conn
					.prepareStatement("insert into CONTACT_INFO(PHONE_NO,NAME,EMAIL) values (?,?,?)");
			stmt.setLong(1, contact.getContactNumber());
			stmt.setString(2, contact.getName());
			stmt.setString(3, contact.getEmail());
			stmt.executeQuery();
		}
	}

	@Override
	public void editContact(final Contact contact, final Long oldContactNumber)
			throws SQLException {
		try (Connection conn = getConnection()) {
			PreparedStatement stmt = conn
					.prepareStatement("update CONTACT_INFO set PHONE_NO = ?,NAME=?,EMAIL=? where PHONE_NO = ? ");
			stmt.setLong(1, contact.getContactNumber());
			stmt.setString(2, contact.getName());
			stmt.setString(3, contact.getEmail());
			stmt.setLong(4, oldContactNumber);
			stmt.executeQuery();
		}
	}

	@Override
	public Contact searchContact(final Long phoneNum) throws SQLException {
		Contact contactVO = null;
		try (Connection conn = getConnection()) {
			PreparedStatement stmt = conn
					.prepareStatement("select * from CONTACT_INFO where PHONE_NO = ?");
			stmt.setLong(1, phoneNum);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				contactVO = new Contact();
				contactVO.setContactNumber(rs.getLong(1));
				contactVO.setName(rs.getString(2));
				contactVO.setEmail(rs.getString(3));
			}
		}
		return contactVO;
	}

	@Override
	public List<Contact> viewAll() throws SQLException {
		List<Contact> results = null;
		Contact contactVO = null;
		try (Connection conn = getConnection()) {
			PreparedStatement stmt = conn
					.prepareStatement("select * from CONTACT_INFO");
			ResultSet rs = stmt.executeQuery();

			results = new ArrayList<Contact>();

			while (rs.next()) {
				contactVO = new Contact();
				contactVO.setContactNumber(rs.getLong(1));
				contactVO.setName(rs.getString(2));
				contactVO.setEmail(rs.getString(3));
				results.add(contactVO);
			}
		}
		return results;
	}

	@Override
	public void delete(final Long phoneNum) throws SQLException {
		try (Connection conn = getConnection()) {
			PreparedStatement stmt = conn
					.prepareStatement("delete from CONTACT_INFO where PHONE_NO = ?");
			stmt.setLong(1, phoneNum);
			stmt.executeQuery();
		}
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		String dbURL2 = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "system";
		String password = "amit";
		conn = DriverManager.getConnection(dbURL2, username, password);
		return conn;
	}

}
