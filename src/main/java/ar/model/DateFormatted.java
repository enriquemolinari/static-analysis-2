package ar.model;

import java.time.LocalDateTime;

public class DateFormatted {
	
	private static final Integer LEN_JUST_DATE = 10;
	private LocalDateTime date;
	
	public DateFormatted(LocalDateTime date) {
		this.date = date;
	}
	
	public DateFormatted(String date) {
		if (LEN_JUST_DATE.equals(date.length()))
			date = date + " 00:00:00.0";
		this.date = LocalDateTime.parse(date, new PatternForDate().toDateTimeFormmater());
	}
	
	public LocalDateTime value() {
		return this.date;
	}
	
	public String strValue() {
		return this.date.format(new PatternForDate().toDateTimeFormmater());
	}
}
