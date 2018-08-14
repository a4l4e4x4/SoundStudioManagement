/**
 * 
 */
package com.tfe.soundstudio.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author alex tolkmitt
 *
 */
@NodeEntity(label="Contact")
public class Contact {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String contactname;
	private String contactsurname;
	private String address;
	private String phone;
	private String email;
	private String city;
	private String postcode;
	private String country;
	
	@Relationship(type="HAS_CONTACT", direction=Relationship.INCOMING)
	private List<Client> clients = new ArrayList<>();
	
	@Relationship(type="HAS_CONTACT", direction=Relationship.INCOMING)
	private List<Musician> musicians = new ArrayList<>();
	
	@Relationship(type="HAS_CONTACT", direction=Relationship.INCOMING)
	private List<Engineer> engineers = new ArrayList<>();
	
	public Contact() {}

	public Contact(Long id, String contactname, String contactsurname, String address, String phone, String email,
			String city, String postcode, String country, List<Client> clients, List<Musician> musicians,
			List<Engineer> engineers) {
		super();
		this.id = id;
		this.contactname = contactname;
		this.contactsurname = contactsurname;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
		this.clients = clients;
		this.musicians = musicians;
		this.engineers = engineers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Musician> getMusicians() {
		return musicians;
	}

	public void setMusicians(List<Musician> musicians) {
		this.musicians = musicians;
	}

	public List<Engineer> getEngineers() {
		return engineers;
	}

	public void setEngineers(List<Engineer> engineers) {
		this.engineers = engineers;
	};

	

}
