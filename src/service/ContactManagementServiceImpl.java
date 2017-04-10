package service;

import java.util.List;

import bean.Contact;
import domain.ContactManagementRepo;
import domain.ContactManagementRepoImpl;

public class ContactManagementServiceImpl implements ContactManagementService {

	@Override
	public void addContact(Contact contact) throws Exception {
		ContactManagementRepo contactRepo = new ContactManagementRepoImpl();
		Contact result = contactRepo.searchContact(contact.getContactNumber());
		if (result == null) {
			contactRepo.addContact(contact);
			System.out.println("Contact added Successfully !!!");
		} else {
			System.out.println("The contact already exists !!!");
		}
	}

	@Override
	public void editContact(final Contact contact, final Long phoneNum)
			throws Exception {
		ContactManagementRepo contactRepo = new ContactManagementRepoImpl();
		contactRepo.editContact(contact, phoneNum);
		System.out.println("Contact updated successfully !!!");
	}

	@Override
	public Contact searchContact(final Contact contact) throws Exception {
		ContactManagementRepo contactRepo = new ContactManagementRepoImpl();
		Contact result = contactRepo.searchContact(contact.getContactNumber());
		if (result != null) {
			System.out.println("Phone Number :" + result.getContactNumber()
					+ "\nName:" + result.getName() + "\nEmail:"
					+ result.getEmail());
		} else {
			System.out.println("Contact does not exists.");
		}
		return result;
	}

	@Override
	public void viewAll() throws Exception {
		ContactManagementRepo contactRepo = new ContactManagementRepoImpl();
		List<Contact> results = contactRepo.viewAll();
		if (results.size() > 0) {
			for (Contact contactVO : results) {
				System.out.println("Phone Number :"
						+ contactVO.getContactNumber() + "\nName:"
						+ contactVO.getName() + "\nEmail:"
						+ contactVO.getEmail() + "\n------");
			}
		} else {
			System.out.println("PhoneBook is empty.");
		}
	}

	@Override
	public void delete(final Contact contact) throws Exception {
		ContactManagementRepo contactRepo = new ContactManagementRepoImpl();
		Contact result = contactRepo.searchContact(contact.getContactNumber());
		if (result != null) {
			contactRepo.delete(contact.getContactNumber());
			System.out.println("Contact deleted successfully !!!");
		} else {
			System.out.println("The contact does not exists.");
		}
	}

	@Override
	public boolean validateContact(final Contact contact) throws Exception {
		ContactManagementRepo contactRepo = new ContactManagementRepoImpl();
		Contact result = contactRepo.searchContact(contact.getContactNumber());
		if (result != null) {
			return true;
		}
		return false;
	}
}
