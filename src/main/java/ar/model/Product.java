package ar.model;

public class Product {

	private Long id;
	private String description;
	private double price;
	private int pointsToGrant = 0;

	protected Product() {

	}

	public Product(String description, int pointToGrant, int price) {
		this.description = description;
		this.pointsToGrant = pointToGrant;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [description=" + description + ", price=" + price + "]";
	}

	public int grantedPoints() {
		return this.pointsToGrant;
	}

	public String description() {
		return this.description;
	}

	public double price() {
		return this.price;
	}

	SalesItem toItem() {
		return new SalesItem(this.id, this.description, this.price);
	}

	String idAsString() {
		return this.id.toString();
	}

	Long id() {
		return this.id;
	}

	private Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	private double getPrice() {
		return price;
	}

	private void setPrice(double price) {
		this.price = price;
	}

	private int getPointsToGrant() {
		return pointsToGrant;
	}

	private void setPointsToGrant(int pointsToGrant) {
		this.pointsToGrant = pointsToGrant;
	}
}
