package ar.model;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class CreditCard {

	private Long id;
	private String number;
	private String company;
	private YearMonth dueDate;
	private static final String PATTERN = "yyyy-MM";

	public CreditCard(Long id, String number, String dueDate) {
		this.id = id;
		var validNumber = new ValidCreditCardNumber(number);
		this.company = validNumber.company();
		this.number = validNumber.number();
		try {
			this.dueDate = YearMonth.parse(dueDate, DateTimeFormatter.ofPattern(PATTERN));
		} catch(DateTimeParseException e) {
			throw new SalesException("Due Date Format must be: " + PATTERN, e);
		}
	}
	
	public CreditCard(String number, String dueDate) {
		this(null, number, dueDate);
	}

	public static CreditCard of(Long id) {
		return new CreditCard(id);
	}
	
	private CreditCard(Long id) {
		this.id = Objects.requireNonNull(id, "Must be a non null Long value");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CreditCard other = (CreditCard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String dueDate() {
		return dueDate.format(DateTimeFormatter.ofPattern(PATTERN));
	}

	public String number() {
		return number;
	}
	
	protected CreditCard() { }

	private Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private String getNumber() {
		return number;
	}

	private void setNumber(String number) {
		this.number = number;
	}

	private String getCompany() {
		return company;
	}

	private void setCompany(String company) {
		this.company = company;
	}

	private YearMonth getDueDate() {
		return dueDate;
	}

	private void setDueDate(YearMonth dueDate) {
		this.dueDate = dueDate;
	}
}
