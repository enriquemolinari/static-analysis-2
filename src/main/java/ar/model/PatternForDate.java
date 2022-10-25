package ar.model;

import java.time.format.DateTimeFormatter;

class PatternForDate {

	private String pattern;
	
	public PatternForDate() {
		this("yyyy-MM-dd");
	}
	
	public PatternForDate(String pattern) {
		this.pattern = pattern;
	}
	
	public DateTimeFormatter toDateFormmater() {
		return DateTimeFormatter.ofPattern(this.pattern);
	}
	
	public DateTimeFormatter toDateTimeFormmater() {
		return DateTimeFormatter.ofPattern(this.pattern + " HH:mm:ss.s");
	}
}
