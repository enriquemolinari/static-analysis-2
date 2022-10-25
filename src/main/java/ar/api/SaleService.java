package ar.api;

import java.util.List;

public interface SaleService {

	Sale confirm(Long clientId, Long idCard, int secCode, List<Long> idProducts, Long idDiscount);

	Sale confirm(Long clientId, Long idCard, int secCode, List<Long> idProducts);
	
	ClientCart clientCart(Long clientId, List<Long> idProducts, Long idDiscount);

	ClientCart clientCart(Long clientId, List<Long> idProducts);

	Sales salesBetween(String from, String to);
	
	Sales purchasesBy(Long clientId);
}
