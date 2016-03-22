package com.weasley.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest") // RESTServlet servlet-mapping (relative to Context Root)
public class WeasleyApp extends Application {
	// Must be explicitly set for Wink
	// RESTEasy will auto-scan class path by default
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(CustomerRESTService.class);
		return classes;
	}
}
