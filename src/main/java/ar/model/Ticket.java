package ar.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ticket {

	private Long id;
	private int earnedPoints;
	private List<SalesItem> items;
	private LocalDateTime salesDate;
	private Client client;
	private double amount;
	private double amountWithDiscount;
	private Discount discount;

	public Ticket(List<SalesItem> items, Client client, int obtainedPointInThisPurchase,
			LocalDateTime date, double totalWithoutDiscount, double totalWithDiscount,
			Discount discount) {

		this.items = items;
		this.earnedPoints = obtainedPointInThisPurchase;
		this.salesDate = date;
		this.client = client;
		this.amountWithDiscount = totalWithDiscount;
		this.amount = totalWithoutDiscount;
		this.discount = discount;
	}

	public Ticket(List<SalesItem> items, Client client, int obtainedPointInThisPurchase,
			LocalDateTime date, double totalWithoutDiscount, double totalWithDiscount) {

		this(items, client, obtainedPointInThisPurchase, date, totalWithoutDiscount,
				totalWithDiscount, null);
	}

	public Long id() {
		return this.id;
	}

	public int earnedPoints() {
		return earnedPoints;
	}

	public int clientPointsAfterDiscount() {
		if (discount != null) 
			return discount.apply(this.client);
		return this.client.totalPoints();
	}
	
	public String date() {
		return new DateFormatted(this.salesDate).strValue();
	}
	
	public List<Map<String, String>> items() {
		return items.stream().map(i -> i.toMap()).collect(Collectors.toList());
	}

	public Double amountWithoutDiscount() {
		return this.amount;
	}

	public Double discount() {
		if (this.discount != null)
			return this.discount.toDiscount(this.amount);
		return 0.0;
	}

	public double total() {
		return this.amountWithDiscount;
	}
	
	protected Ticket() {

	}


	private Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private int getEarnedPoints() {
		return earnedPoints;
	}

	private void setEarnedPoints(int earnedPoints) {
		this.earnedPoints = earnedPoints;
	}

	private List<SalesItem> getItems() {
		return items;
	}

	private void setItems(List<SalesItem> items) {
		this.items = items;
	}

	private LocalDateTime getSalesDate() {
		return salesDate;
	}

	private void setSalesDate(LocalDateTime salesDate) {
		this.salesDate = salesDate;
	}

	private Client getClient() {
		return client;
	}

	private void setClient(Client client) {
		this.client = client;
	}

	private double getAmount() {
		return amount;
	}

	private void setAmount(double amount) {
		this.amount = amount;
	}

	private double getAmountWithDiscount() {
		return amountWithDiscount;
	}

	private void setAmountWithDiscount(double amountWithDiscount) {
		this.amountWithDiscount = amountWithDiscount;
	}

	private Discount getDiscount() {
		return discount;
	}

	private void setDiscount(Discount discount) {
		this.discount = discount;
	}
}
