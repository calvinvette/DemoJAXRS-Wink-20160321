package com.weasley.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.weasley.data.Customer;

public class CustomerJPADAO implements CustomerDAO {
	// JPA Uses Dependency Injection
	// PersistenceContext uses EntityManagerFactory to push in an instance
	// But ONLY in a "managed" environment like
	// Full JEE: JBoss Wildfly, WebSphere, GlassFish
	// But not in non-managed environments like:
	// Web Profile JEE - Tomcat, Jetty
	// Swing, JavaFX, Java "main" apps, JUnit tests
	@Inject
	@PersistenceContext
	private EntityManager entityManager;

	// Use EntityManagerFactory in non-managed cases
	@PersistenceUnit(unitName = "DemoJAXRS")
	private static EntityManagerFactory entityManagerFactory;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			System.out.println("Manually creating EMF");
			// Painful, 1-time bootstrapping
			// Keep this semi-permanently in memory during application lifetime
			entityManagerFactory = Persistence.createEntityManagerFactory("DemoJAXRS");
		}
		return entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		if (entityManager == null) {
			System.out.println("Manually creating EM");
			entityManager = getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Transactional
	public Customer insert(Customer customer) {
		getEntityManager().persist(customer);
		return customer;
	}

	@Override
	public Customer findById(Long customerId) {
		return getEntityManager().find(Customer.class, customerId);
	}

	@Override
	public List<Customer> findAll() {
		return getEntityManager().createNamedQuery(Customer.FIND_ALL, Customer.class).getResultList();
	}

	@Override
	public List<Customer> findByLastName(String lastName) {
		return getEntityManager().createNamedQuery(Customer.FIND_BY_LASTNAME, Customer.class)
				.setParameter("lastName", lastName)
				.getResultList();
	}
	
	@Override
	public List<Customer> findByFirstNameLastName(String firstName, String lastName) {
		return getEntityManager().createNamedQuery(Customer.FIND_BY_FIRSTNAME_LASTNAME, Customer.class)
				.setParameter("lastName", lastName)
				.setParameter("firstName", firstName)
				.getResultList();
	}
	
	@Override
	public List<Customer> findByEmail(String email) {
		return getEntityManager().createNamedQuery(Customer.FIND_BY_EMAIL, Customer.class)
				.setParameter("email", email)
				.getResultList();
	}
	
	@Override
	public List<Customer> findByPhoneNumber(String phoneNumber) {
		return getEntityManager().createNamedQuery(Customer.FIND_BY_PHONENUMBER, Customer.class)
				.setParameter("phoneNumber", phoneNumber)
				.getResultList();
	}

	@Override
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Transactional
	public List<Customer> update(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Transactional
	public Customer delete(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Transactional
	public List<Customer> delete(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
