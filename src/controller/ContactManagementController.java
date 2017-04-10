package controller;

import java.util.Scanner;

import service.ContactManagementService;
import service.ContactManagementServiceImpl;
import bean.Contact;

/**
 * 
 * @author AMIT
 * @class ContactManagementController
 * 
 */
public class ContactManagementController {

	public static void main(String[] args) {
		showMessage();
		Scanner in = new Scanner(System.in);
		switch (in.nextInt()) {
		case 1:
			add();
			break;
		case 2:
			search();
			break;
		case 3:
			viewAll();
			break;
		case 4:
			edit();
			break;
		case 5:
			delete();
			break;
		default:
			System.out
					.println("Input choice is invalid.Program is terminated.");
		}
		in.close();
	}

	/**
	 * Method to show the options to user.
	 */
	private static void showMessage() {
		StringBuilder s = new StringBuilder();
		s.append("***** PhoneBook Application*****\n\n")
				.append("Please select any operation to be performed :\n1.Add a new Contact.\n2.Search a contact by phone number.\n3.View All contacts.")
				.append("\n4.Edit an existing Contact.\n5.Delete a contact.")
				.append("\nEnter your choice");
		System.out.println(s.toString());
	}

	/**
	 * Method to Add a new contact. Adds a contact only if it the contact does
	 * not present in DB.
	 */
	private static void add() {
		ContactManagementService contactService = new ContactManagementServiceImpl();
		Contact contact = new Contact();
		try (Scanner in = new Scanner(System.in)) {
			in.useDelimiter(System.getProperty("line.separator"));
			System.out.println("\nEnter Phone Number");
			contact.setContactNumber(in.nextLong());
			System.out.println("\nEnter Name");
			contact.setName(in.next());
			System.out.println("\nEnter Email");
			contact.setEmail(in.next());
			try {
				contactService.addContact(contact);
			} catch (Exception e) {
				System.out.println("\nSystem encountered some Internal error.");
			}
		} catch (Exception e) {
			System.out.println("\nInvalid user input.Program is terminated.");
		}
	}

	/**
	 * Edit an existing contact. If the contact is not found , it will show the
	 * appropriate error message to the end user.
	 */
	private static void edit() {
		ContactManagementService contactService = new ContactManagementServiceImpl();
		Contact contact = new Contact();
		try (Scanner in = new Scanner(System.in)) {
			in.useDelimiter(System.getProperty("line.separator"));
			System.out
					.println("\nEnter the phone number for which details has to be edited");
			Long phoneNum = in.nextLong();
			contact.setContactNumber(phoneNum);
			boolean flag = contactService.validateContact(contact);
			if (flag) {
				try {
					System.out.println("\nEnter new phone number");
					contact.setContactNumber(in.nextLong());
					System.out.println("\nEnter new name");
					contact.setName(in.next());
					System.out.println("\nEnter new email");
					contact.setEmail(in.next());
				} catch (Exception e) {
					System.out
							.println("\nInvalid user input.Program is terminated.");
					return;
				}
				try {
					contactService.editContact(contact, phoneNum);
				} catch (Exception e) {
					System.out
							.println("\nSystem encountered some Internal error.");
				}
			} else {
				System.out.println("Contact does not exists.");
			}
		} catch (Exception e) {
			System.out.println("\nInvalid user input.Program is terminated.");
		}
	}

	/**
	 * Search a contact with phone number.
	 */
	private static void search() {
		ContactManagementService contactService = new ContactManagementServiceImpl();
		Contact contact = new Contact();
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("\nEnter phone number");
			contact.setContactNumber(in.nextLong());
			try {
				contactService.searchContact(contact);
			} catch (Exception e) {
				System.out.println("\nSystem encountered some Internal error.");
			}
		} catch (Exception e) {
			System.out.println("\nInvalid user input.Program is terminated.");
		}
	}

	/**
	 * Displays all the contacts present in DB.
	 */
	private static void viewAll() {
		ContactManagementService contactService = new ContactManagementServiceImpl();
		try {
			contactService.viewAll();
		} catch (Exception e) {
			System.out.println("\nSystem encountered some Internal error.");
		}
	}

	/**
	 * Delete an existing contact. If the contact is not found , it will show
	 * the appropriate message to user.
	 */
	private static void delete() {
		ContactManagementService contactService = new ContactManagementServiceImpl();
		Contact contact = new Contact();
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("\nEnter phone number to be deleted");
			contact.setContactNumber(in.nextLong());
			try {
				contactService.delete(contact);
			} catch (Exception e) {
				System.out.println("\nSystem encountered some Internal error.");
			}
		} catch (Exception e) {
			System.out.println("\nInvalid user input.Program is terminated.");
		}
	}

}
