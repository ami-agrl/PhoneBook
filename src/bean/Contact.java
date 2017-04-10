package bean;

/**
 * 
 * @author AMIT
 * @class Contact
 *
 */
public class Contact {

	/**
	 * Phone Number
	 */
	private Long contactNumber;

	/**
	 * Name.
	 */
	private String name;

	/**
	 * Email
	 */
	private String email;

	/**
	 * @return contactNumber
	 */
	public Long getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 */
	public void setContactNumber(final Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

}
