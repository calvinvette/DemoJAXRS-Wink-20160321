package com.weasley.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.enterprise.inject.Alternative;

import com.weasley.data.Customer;

@Alternative
public class CustomerMockDAO implements CustomerDAO {
	private static Map<Long, Customer> customersById = new HashMap<>();
	private static Long lastCustomerId = 0L;

	static {
		customersById.put(++lastCustomerId,
				new Customer(lastCustomerId, "Harry", "Potter", "555-1212", "harry@hogwarts.ac.uk"));
		customersById.put(++lastCustomerId,
				new Customer(lastCustomerId, "Ron", "Weasley", "555-1213", "ron@hogwarts.ac.uk"));
		customersById.put(++lastCustomerId,
				new Customer(lastCustomerId, "Hermione", "Granger", "555-1214", "hermione@hogwarts.ac.uk"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weasley.dao.CustomerDAO#findById(java.lang.Long)
	 */
	@Override
	public Customer findById(Long customerId) {
		return customersById.get(customerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weasley.dao.CustomerDAO#findAll()
	 */
	@Override
	public List<Customer> findAll() {
		return new Vector<Customer>(customersById.values());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weasley.dao.CustomerDAO#findByLastName(java.lang.String)
	 */
	@Override
	public List<Customer> findByLastName(String lastName) {
		List<Customer> results = new Vector<>();
		for (Customer c : customersById.values()) {
			if (c.getLastName().equalsIgnoreCase(lastName)) {
				results.add(c);
			}
		}
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weasley.dao.CustomerDAO#insert(com.weasley.data.Customer)
	 */
	@Override
	public Customer insert(Customer customer) {
		customer.setCustomerId(++lastCustomerId);
		customersById.put(lastCustomerId, customer);
		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weasley.dao.CustomerDAO#delete(com.weasley.data.Customer)
	 */
	@Override
	public List<Customer> delete(Customer customer) { // Query By Example
		List<Customer> results = new Vector<>();
		for (Customer c : customersById.values()) {
			if ((customer.getLastName() != null) && (c.getLastName().equalsIgnoreCase(customer.getLastName()))
					&& (customer.getFirstName() != null)
					&& (c.getFirstName().equalsIgnoreCase(customer.getFirstName()))) {
				customersById.remove(c.getCustomerId());
				results.add(c);
			}
		}
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weasley.dao.CustomerDAO#delete(com.weasley.data.Customer)
	 */
	@Override
	public Customer delete(Long customerId) {
		Customer customer = customersById.remove(customerId);
		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weasley.dao.CustomerDAO#update(com.weasley.data.Customer)
	 */
	// TODO-CV: This logic probably isn't quite right. Create unit tests and fix
	@Override
	public List<Customer> update(Customer customer) { // Query By Example
		List<Customer> results = new Vector<>();
		for (Customer c : customersById.values()) {
			boolean addToResult = false;
			if ((customer.getLastName() != null) && (c.getLastName().equalsIgnoreCase(customer.getLastName()))) {
				c.setLastName(customer.getLastName());
				addToResult = true;
			}
			if ((customer.getFirstName() != null) && (c.getFirstName().equalsIgnoreCase(customer.getFirstName()))) {
				c.setFirstName(customer.getFirstName());
				addToResult = true;
			}
			if (addToResult) {
				customersById.put(c.getCustomerId(), customer);
				results.add(c);
			}
		}
		return results;
	}

	@Override
	public List<Customer> findByFirstNameLastName(String firstName, String lastName) {
		List<Customer> results = new Vector<>();
		for (Customer c : customersById.values()) {
			if (c.getLastName().equalsIgnoreCase(lastName) && c.getFirstName().equalsIgnoreCase(firstName)) {
				results.add(c);
			}
		}
		return results;
	}

	@Override
	public List<Customer> findByPhoneNumber(String phoneNumber) {
		List<Customer> results = new Vector<>();
		for (Customer c : customersById.values()) {
			if (c.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
				results.add(c);
			}
		}
		return results;
	}

	@Override
	public List<Customer> findByEmail(String email) {
		List<Customer> results = new Vector<>();
		for (Customer c : customersById.values()) {
			if (c.getEmail().equalsIgnoreCase(email)) {
				results.add(c);
			}
		}
		return results;
	}

}
