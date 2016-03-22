package com.weasley.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


// Should be generated from Model-Driven Engineering system, along with 
// RESTService, DAO, Table, Angular Entity/Service/Views, iOS Entity/Services/Views, Android Entity/Services/View
// And Unit/Integration/End-to-End, Performance, Scalability, High-Availability, and Security tests
// Customer becomes "Exemplar", then "templatize" it into a generator and run the rest of the entities through

// JPA
// JSR303 Bean Validation
// JAXB - Java API for XML: Binding
/*
<customer>
	<customerId>1234</customerId>
	<firstName>Harry</firstName>
	<lastName>Potter</lastName>
	<phoneNumber>+44 0206 551-3188</phoneNumber>
	<email>harry.potter@hogwarts.ac.uk</email>
</customer>
 */
@XmlType(propOrder={"customerId", "firstName", "lastName", "phoneNumber", "email"})
@XmlRootElement
@Entity
@Table(schema="WEASLEY", name="Customer")
@NamedQueries({
	@NamedQuery(name=Customer.FIND_ALL, query="select c from Customer c"),
	@NamedQuery(name=Customer.FIND_BY_LASTNAME, query="select c from Customer c where c.lastName = :lastName"),
	@NamedQuery(name=Customer.FIND_BY_FIRSTNAME_LASTNAME, query="select c from Customer c where c.lastName = :lastName and c.firstName = :firstName"),
	@NamedQuery(name=Customer.FIND_BY_PHONENUMBER, query="select c from Customer c where c.phoneNumber = :phoneNumber"),
	@NamedQuery(name=Customer.FIND_BY_EMAIL, query="select c from Customer c where c.email = :email"),
})
public class Customer implements Serializable {
	public static final String FIND_ALL = "Customer.FIND_ALL";
	public static final String FIND_BY_LASTNAME = "Customer.FIND_BY_LASTNAME";
	public static final String FIND_BY_FIRSTNAME_LASTNAME = "Customer.FIND_BY_FIRSTNAME_LASTNAME";
	public static final String FIND_BY_PHONENUMBER = "Customer.FIND_BY_PHONENUMBER";
	public static final String FIND_BY_EMAIL = "Customer.FIND_BY_EMAIL";
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customerId = -1L;
	private String firstName, lastName, phoneNumber, email;

	public Customer() {
	}

	public Customer(String firstName, String lastName, String phoneNumber, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Customer(Long customerId, String firstName, String lastName, String phoneNumber, String email) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

}
