package unitTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import service.ContactManagementService;
import service.ContactManagementServiceImpl;
import bean.Contact;

public class PhoneBookTest {

	public static Connection conn = null;

	@Before
	public void openConnection() {
		String dbURL2 = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "system";
		String password = "amit";
		try {
			conn = DriverManager.getConnection(dbURL2, username, password);
		} catch (SQLException e) {
			System.out.println("Unable to create connection.");
		}
	}

	@Test
	public void testAddContact() {
		ContactManagementService contactService = new ContactManagementServiceImpl();
		Contact contact = new Contact();
		long phoneNumber = 9176185370L;
		String name = "Amit Agarwal";
		String email = "agarwalamit272@gmail.com";
		contact.setContactNumber(phoneNumber);
		contact.setName(name);
		contact.setEmail(email);
		try {
			contactService.addContact(contact);
			ResultSet rs = getResultSet(phoneNumber);
			Assert.assertNotNull(rs);
			while (rs.next()) {
				Assert.assertNotNull(rs.getLong(1));
				Assert.assertNotNull(rs.getString(2));
				Assert.assertNotNull(rs.getString(3));
				Assert.assertEquals(phoneNumber, rs.getLong(1));
				Assert.assertEquals(name, rs.getString(2));
				Assert.assertEquals(email, rs.getString(3));
			}
		} catch (Exception e) {
			System.out.println("System error Occurred.");
		}
	}

	@Test
	public void testEditContact() {
		ContactManagementService contactService = new ContactManagementServiceImpl();
		Contact contact = new Contact();
		long phoneNumber = 9176185370L;
		String name = "Amit Agarwal";
		String email = "agarwalamit272@gmail.com";
		contact.setContactNumber(phoneNumber);
		contact.setName(name);
		contact.setEmail(email);
		try {
			boolean flag = contactService.validateContact(contact);
			if (flag) {
				contactService.editContact(contact, phoneNumber);
				ResultSet rs = getResultSet(phoneNumber);
				Assert.assertNotNull(rs);
				while (rs.next()) {
					Assert.assertNotNull(rs.getLong(1));
					Assert.assertNotNull(rs.getString(2));
					Assert.assertNotNull(rs.getString(3));
					Assert.assertEquals(phoneNumber, rs.getLong(1));
					Assert.assertEquals(name, rs.getString(2));
					Assert.assertEquals(email, rs.getString(3));
				}
			} else {
				Assert.assertFalse(flag);
			}
		} catch (Exception e) {
			System.out.println("System error Occurred.");
		}
	}

	@Test
	public void testSearch() {
		ContactManagementService contactService = new ContactManagementServiceImpl();
		Contact contact = new Contact();
		long phoneNumber = 9176185370L;
		contact.setContactNumber(phoneNumber);

		try {
			Contact result = contactService.searchContact(contact);
			if (result != null) {
				ResultSet rs = getResultSet(phoneNumber);
				Assert.assertNotNull(rs);
				Assert.assertNotNull(result);
				while (rs.next()) {
					Assert.assertNotNull(result.getContactNumber());
					Assert.assertEquals((long) result.getContactNumber(),
							rs.getLong(1));
				}

			}
		} catch (Exception e) {
			System.out.println("System error Occurred.");
		}
	}

	@Test
	public void testDelete() {
		ContactManagementService contactService = new ContactManagementServiceImpl();
		Contact contact = new Contact();
		long phoneNumber = 9176185370L;
		contact.setContactNumber(phoneNumber);
		try {
			contactService.delete(contact);
			ResultSet rs = getResultSet(phoneNumber);
			Assert.assertNotNull(rs);
			Assert.assertFalse(rs.next());
		} catch (Exception e) {
			System.out.println("System error Occurred.");
		}
	}

	@After
	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Unable to close connection.");
			}
		}
	}

	private ResultSet getResultSet(long phoneNumber) throws SQLException {
		PreparedStatement stmt = conn
				.prepareStatement("select * from CONTACT_INFO where PHONE_NO = ?");
		stmt.setLong(1, phoneNumber);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
}