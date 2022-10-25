package ar.api;

import java.util.Map;

public interface Promotion {

	Long id();

	Map<String, String> represent();

	String validFrom();
	
	String validTo();
}
