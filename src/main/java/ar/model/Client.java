package ar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Client {
	
	private Long id;
	private List<CreditCard> creditCards;
	private List<Ticket> purchases;
	private int points;
	private String name;
	private String surname;

	public Client(String name, String surname, List<CreditCard> cards, int initialPoints) {
		this.creditCards = Objects.requireNonNull(cards, "Must have at least a credit card");
		this.points = initialPoints;
		this.purchases = new ArrayList<>();
		this.name = Objects.requireNonNull(name, "Must indicate your name");
		this.surname = Objects.requireNonNull(surname, "Must indicate your surname");
	}

	protected Client() {}
	
	public Client(String name, String surname, List<CreditCard> creditCards) {
		this(name, surname, creditCards, 0);
	}

	public Client(String name, String surname) {
		this(name, surname, new ArrayList<>(), 0);
	}

	public int totalPoints() {
		return points;
	}

	public void addCreditCard(String number, String dueDate) {
		this.creditCards.add(new CreditCard(number, dueDate));
	}
	
	public Optional<CreditCard> creditCard(Long cardId) {
		return creditCards.stream().filter(t -> t.equals(CreditCard.of(cardId))).findFirst();
	}
	
	public void purchase(Ticket ticket) {
		this.purchases.add(ticket);
		this.points = ticket.clientPointsAfterDiscount();
		this.points += ticket.earnedPoints();
	}
	
	public Optional<Ticket> lastPurchase() {
		if (purchases.size() > 0)
			return Optional.of(purchases.get(purchases.size() - 1));
		return Optional.empty();
	}

	private List<CreditCard> getCreditCards() {
		return creditCards;
	}

	private void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	private List<Ticket> getPurchases() {
		return purchases;
	}

	private void setPurchases(List<Ticket> purchases) {
		this.purchases = purchases;
	}

	private int getPoints() {
		return points;
	}

	private void setPoints(int points) {
		this.points = points;
	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private String getSurname() {
		return surname;
	}

	private void setSurname(String surname) {
		this.surname = surname;
	}

	private Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}
}
