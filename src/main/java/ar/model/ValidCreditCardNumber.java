package ar.model;

public class ValidCreditCardNumber {

	private String number;
	private String company;
	
	public ValidCreditCardNumber(String number) {
		//logic to validate credit card number
		
		this.number = number;
		this.company = determinarCompania();
	}

	private String determinarCompania() {
		//to determine company name from the number
		
		return null;
	}

	public String company() {
		return company;
	}

	public String number() {
		return number;
	}
}
