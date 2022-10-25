package ar.model;

import java.time.LocalDateTime;

public class DateRange {

	private LocalDateTime validFrom;
	private LocalDateTime validTo;

	public DateRange(String from, String to) {
		this.validFrom = new DateFormatted(from).value();
		this.validTo = new DateFormatted(to).value();
	}

	public boolean inRange(String date) {
		var dateAsLocalDate = new DateFormatted(date).value();

		return (dateAsLocalDate.isAfter(validFrom) || dateAsLocalDate.isEqual(validFrom))
				&& (dateAsLocalDate.isBefore(validTo)
						|| dateAsLocalDate.isEqual(validTo));
	}

	protected DateRange() {
	}

	String from() {
		return new DateFormatted(this.validFrom).strValue();
	}
	
	String to() {
		return new DateFormatted(this.validTo).strValue();
	}

	private LocalDateTime getValidFrom() {
		return validFrom;
	}

	private void setValidFrom(LocalDateTime validFrom) {
		this.validFrom = validFrom;
	}

	private LocalDateTime getValidTo() {
		return validTo;
	}

	private void setValidTo(LocalDateTime validTo) {
		this.validTo = validTo;
	}
}
