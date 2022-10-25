package ar.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cart {

	private List<Product> products;
	private Optional<Discount> discount;

	public Cart(List<Product> products, Discount discount) {
		this.products = products;
		this.discount = Objects.requireNonNull(Optional.of(discount),
				"Should use a valid discount...");
	}

	public Cart(List<Product> products) {
		this.products = products;
		this.discount = Optional.empty();
	}

	private double total() {
		return products.stream().mapToDouble(p -> p.price()).sum();
	}

	public double amountWithDiscount() {
		return discount.map(d -> {
			return d.amountToPay(this.total());
		}).orElse(this.total());
	}

	private List<SalesItem> products() {
		return products.stream().map(p -> p.toItem()).collect(Collectors.toList());
	}

	public int obtainedPointsFromThisPurchase() {
		return products.stream().mapToInt(p -> p.grantedPoints()).sum();
	}

	public Ticket emitTicket(Client client) {
		return new Ticket(this.products(), client, this.obtainedPointsFromThisPurchase(),
				LocalDateTime.now(), this.total(), this.amountWithDiscount(),
				discount.orElse(null));
	}
}
