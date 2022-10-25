package ar.model;

import java.util.Objects;

public class OnlinePayment {

	private PaymentProvider provider;
	private Cart cart;
	private Client client;

	public OnlinePayment(PaymentProvider provider, Cart cart, Client client) {
		this.provider = Objects.requireNonNull(provider,
				"Must provide a payment provider");
		this.cart = cart;
		this.client = client;
	}

	public Ticket confirm(CreditCard card, int securityCode) {
		Ticket ticket = this.cart.emitTicket(client);

		client.purchase(ticket);

		double amount = this.cart.amountWithDiscount();

		this.provider.execute(amount, card.number(), securityCode);

		return ticket;
	}
}
