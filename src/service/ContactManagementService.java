package service;

import bean.Contact;

public interface ContactManagementService {

	/**
	 * Adds a contact.
	 */
	public void addContact(final Contact contact) throws Exception;

	/**
	 * Edit/Modify any existing contact.
	 */
	public void editContact(final Contact contact, final Long phoneNum)
			throws Exception;

	/**
	 * Search a contact.
	 */
	public Contact searchContact(final Contact contact) throws Exception;

	/**
	 * View All contact.
	 */
	public void viewAll() throws Exception;

	/**
	 * Delete an existing contact.
	 */
	public void delete(final Contact contact) throws Exception;

	/**
	 * Validates a contact is present or not.
	 */
	public boolean validateContact(final Contact contact) throws Exception;

}
