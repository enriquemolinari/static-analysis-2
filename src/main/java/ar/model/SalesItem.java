package ar.model;

import java.util.Map;

//This class should not be needed in a pure object oriented app
//but I need it in order do duplicate info of a sold product
public class SalesItem {

	private Long salesItemId;
	private String description;
	private double price;
	
	public SalesItem(Long salesItemId, String description, double price) {
		this.salesItemId = salesItemId;
		this.description = description;
		this.price = price;
	}
	
	protected SalesItem() {}

	public String description() {
		return description;
	}
	
	public double price() {
		return price;
	}
	
	public Map<String, String> toMap() {
		return Map.of("id", salesItemId.toString(),
					  "description", description,
					  "price", String.valueOf(price));
	}

	private Long getSalesItemId() {
		return salesItemId;
	}

	private void setSalesItemId(Long salesItemId) {
		this.salesItemId = salesItemId;
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
}
