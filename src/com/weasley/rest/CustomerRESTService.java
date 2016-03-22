package com.weasley.rest;


import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.weasley.dao.CustomerDAO;
import com.weasley.dao.CustomerJPADAO;
import com.weasley.dao.CustomerMockDAO;
import com.weasley.data.Customer;

// http://localhost:8080/HelloJEEWeb <-- Context Root
// http://localhost:8080/HelloJEEWeb/rest <-- REST Servlet's Servlet Mapping
//		(set in either the web.xml through config variables or an @ApplicationPath/Application)
// http://localhost:8080/HelloJEEWeb/rest/customers <-- This class' base URL
@Path("/customers")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
// Add in Java Web Security API (JAAS, @RolesAllowed)
public class CustomerRESTService {
	// TODO-CV
	// @Inject - use CDI Injection to dynamically inject either MockDAO or JPADAO
//	CustomerDAO dao = new CustomerMockDAO(); // for now, directly instantiate the MockDAO
	CustomerDAO dao = new CustomerJPADAO(); // for now, directly instantiate the MockDAO

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
	public List<Customer> findAll(
			@QueryParam("start") @DefaultValue("0") int start, 
			@QueryParam("size") @DefaultValue("3") int size,
			@HeaderParam("User-Agent") String browser,
			@Context UriInfo uriInfo
			) {
		List<Customer> found = dao.findAll();
		// Make sure start and start+size are not bigger than the actual list!
		if (found.size() < start) {			
			start = found.size() - size;	
			if (start < 0) {
				start = 0;
			}
		}
		if (found.size() < (start+size)) {	
			size = (found.size() - start); 	
		}
		List<Customer> result = found.subList(start, start+size);
		System.out.println("Browser: " + browser);
//		uriInfo.
		return result;
	}

	@GET
	// http://localhost:8080/HelloJEEWeb/rest/customers/lastName/Weasley
	@Path("/lastName/{lastName}")
	// http://localhost:8080/HelloJEEWeb/rest/customers/Weasley
//	@Path("{lastName}")
	public List<Customer> findByLastName(@PathParam("lastName") String lastName) {
		return dao.findByLastName(lastName);
	}
	
//	@GET
////	 http://localhost:8080/HelloJEEWeb/rest/customers/firstName/George
//	@Path("/firstName/{firstName}")
//	// http://localhost:8080/HelloJEEWeb/rest/customers/George
////	@Path("{firstName}")
//	public List<Customer> findByFirstName(@PathParam("firstName") String firstName) {
//		return dao.findByLastName(firstName); // TODO-CV add the findByFirstName to dao to support this
//	}
	
	@GET
	// http://localhost:8080/HelloJEEWeb/rest/customers/lastName/Weasley/firstName/George
	@Path("/lastName/{lastName}/firstName/{firstName}")
	public List<Customer> findByFirstAndLastName(
			@PathParam("firstName") String firstName,
			@PathParam("lastName") String lastName
		) {
		return dao.findByFirstNameLastName(firstName, lastName); 
	}
	
	@GET
	// http://localhost:8080/HelloJEEWeb/rest/customers/email/harry.potter@hogwarts.ac.uk
	@Path("/email/{email}")
	public List<Customer> findByEmail(
			@PathParam("email") String email
			) {
		return dao.findByEmail(email); 
	}
	
	@GET
	// http://localhost:8080/HelloJEEWeb/rest/customers/phoneNumber/555-1212
	@Path("/phoneNumber/{phoneNumber}")
	public List<Customer> findByPhoneNumber(
			@PathParam("phoneNumber") String phoneNumber
			) {
		return dao.findByPhoneNumber(phoneNumber); 
	}
	

	@PUT
	public List<Customer> update(Customer customer) {
		return dao.update(customer);
	}

	@DELETE
	@RolesAllowed({"Admin", "CustomerServiceManager"})
	// http://localhost:8080/HelloJEEWeb/rest/customers/1234
	@Path("{id: \\d+}")
	public Customer delete(@PathParam("id") Long customerId) {
		return dao.delete(customerId);
	}

	@DELETE
	@RolesAllowed({"Admin", "CustomerServiceManager"})
	public List<Customer> delete(Customer customer) { // Delete By Example (Query By Example)
		return dao.delete(customer);
	}
	
	
	
}
