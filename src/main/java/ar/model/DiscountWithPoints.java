package ar.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DiscountWithPoints extends Discount {

	private int requiredPoints;
	private double percentage;

	public DiscountWithPoints(double percentage, int points, String from, String to) {
		super(from, to);
		this.percentage = percentage;
		this.requiredPoints = points;
	}

	public double toPay(double amount) {
		return this.applyDiscount(amount);
	}

	public double toDiscount(double amount) {
		return amount * this.percentage;
	}

	protected int apply(Client client) {
		if (requiredPoints > client.totalPoints())
			throw new SalesException("Not enough points");
		return client.totalPoints() - requiredPoints;
	}

	private double applyDiscount(double amount) {
		return amount - (amount * percentage);
	}

	protected DiscountWithPoints() {

	}

	@Override
	public Map<String, String> toRepresent() {
		var map = new HashMap<String, String>();
		map.put("type", "points-and-percentage");
		map.put("requiredPoints", String.valueOf(this.requiredPoints));
		map.put("percentage", String.valueOf(this.percentage));
		return Collections.unmodifiableMap(map);
	}

	private int getRequiredPoints() {
		return requiredPoints;
	}

	private void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}

	private double getPercentage() {
		return percentage;
	}

	private void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	
}
