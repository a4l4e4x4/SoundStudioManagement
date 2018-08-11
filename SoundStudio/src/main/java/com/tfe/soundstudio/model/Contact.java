/**
 * 
 */
package com.tfe.soundstudio.model;

/**
 * @author alex tolkmitt
 *
 */
public class Contact {
	
	private String contactname;
	private String contactsurname;
	private String address;
	private String phone;
	private String email;
	
	public Contact() {}

	public Contact(String contactname, String contactsurname, String address, String phone, String email) {
		super();
		this.contactname = contactname;
		this.contactsurname = contactsurname;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactsurname() {
		return contactsurname;
	}

	public void setContactsurname(String contactsurname) {
		this.contactsurname = contactsurname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	};
	
	

}
