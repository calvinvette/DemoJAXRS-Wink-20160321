package com.weasley.dao;

import java.util.List;

import com.weasley.data.Customer;

public interface CustomerDAO {
	public Customer insert(Customer customer);
	public Customer findById(Long customerId);
	public List<Customer> findAll();
	public List<Customer> findByLastName(String lastName);
	public List<Customer> update(Customer customer);
	public Customer delete(Long customerId);
	public List<Customer> delete(Customer customer);
}
