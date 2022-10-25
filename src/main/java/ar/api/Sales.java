package ar.api;

public interface Sales {

	Iterable<Sale> sales();

	Double total();
}
