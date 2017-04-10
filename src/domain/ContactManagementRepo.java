package domain;

import java.util.List;

import bean.Contact;

public interface ContactManagementRepo {

	/**
	 * Adds a contact.
	 */
	public void addContact(final Contact contactVO) throws Exception;

	/**
	 * Edit/Modify any existing contact.
	 */
	public void editContact(final Contact contactVO, final Long oldContactNumber)
			throws Exception;

	/**
	 * Search a contact.
	 */
	public Contact searchContact(final Long phoneNum) throws Exception;

	/**
	 * View All contacts.
	 */
	public List<Contact> viewAll() throws Exception;

	/**
	 * Delete a contact.
	 */
	public void delete(final Long phoneNum) throws Exception;
}
