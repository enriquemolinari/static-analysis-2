package ar.api;

import java.util.Map;

public interface PurchaseDetail {

	double total();

	double totalWithoutDiscount();
	
	int pointsToWin();
	
	String date();
	
	Iterable<Map<String, String>> items();
}
