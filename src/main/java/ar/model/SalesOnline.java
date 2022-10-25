package ar.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SalesOnline {

	Optional<Client> client(Long id);

	Discount discount(Long id);

	List<Product> products(List<Long> ids);

	List<Discount> discountsIn(LocalDateTime fecha);

	List<Ticket> salesBetween(String desde, String hasta);

	List<Ticket> purchasesFrom(Long idCliente);
}
