package ar.model;

import java.time.LocalDateTime;
import java.util.Map;

public abstract class Discount {

	private Long id;
	private DateRange discountInDate;
	
	public abstract double toPay(double monto);
	public abstract double toDiscount(double monto);
	protected abstract int apply(Client c);
	
	public abstract Map<String, String> toRepresent();
	
	protected Discount(String from, String to) {
		this.discountInDate = new DateRange(from, to);
	}

	public double amountToPay(double total) {
		if (!discountInDate
				.inRange(new DateFormatted(LocalDateTime.now()).strValue()))
			throw new SalesException("Promotion is not available");

		return this.toPay(total);
	}
	
	protected Discount() { }

	public String validoDesde() {
		return this.discountInDate.from();
	}

	public String validoHasta() {
		return this.discountInDate.to();
	}

	public Long id() {
		return this.id;
	}
	
	private Long getId() {
		return id;
	}
	private void setId(Long id) {
		this.id = id;
	}
	private DateRange getDiscountInDate() {
		return discountInDate;
	}
	private void setDiscountInDate(DateRange discountInDate) {
		this.discountInDate = discountInDate;
	}
}
