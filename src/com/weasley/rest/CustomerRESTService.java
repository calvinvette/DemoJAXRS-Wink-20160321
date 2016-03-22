package com.weasley.rest;


import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.weasley.dao.CustomerDAO;
import com.weasley.dao.CustomerMockDAO;
import com.weasley.data.Customer;

// http://localhost:8080/HelloJEEWeb <-- Context Root
// http://localhost:8080/HelloJEEWeb/rest <-- REST Servlet's Servlet Mapping
// http://localhost:8080/HelloJEEWeb/rest/customers <-- This class' base URL
@Path("/customers")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

// Add in Java Web Security API (JAAS, @RolesAllowed)
public class CustomerRESTService {
	// TODO-CV
	// @Inject - use CDI Injection to dynamically inject either MockDAO or JPADAO
	CustomerDAO dao = new CustomerMockDAO(); // for now, directly instantiate the MockDAO

	@POST
	public Customer insert(Customer customer) {
		return dao.insert(customer);
	}

	@GET
	// http://localhost:8080/HelloJEEWeb/rest/customers/1234
	@Path("{id: \\d+}")
	public Customer findById(@PathParam("id") Long customerId) {
		return dao.findById(customerId);
	}

	@GET
	// http://localhost:8080/HelloJEEWeb/rest/customers
	public List<Customer> findAll() {
		return dao.findAll();
	}

	@GET
	// http://localhost:8080/HelloJEEWeb/rest/customers/lastName/Weasley
	@Path("/lastName/{lastName}")
	public List<Customer> findByLastName(@PathParam("lastName") String lastName) {
		return dao.findByLastName(lastName);
	}

	@PUT
	public List<Customer> update(Customer customer) {
		return dao.update(customer);
	}

	@DELETE
	@RolesAllowed({"Admin", "CustomerServiceManager"})
	public Customer delete(Long customerId) {
		return dao.delete(customerId);
	}

	@DELETE
	@RolesAllowed({"Admin", "CustomerServiceManager"})
	public List<Customer> delete(Customer customer) {
		return dao.delete(customer);
	}
	
	
	
}
