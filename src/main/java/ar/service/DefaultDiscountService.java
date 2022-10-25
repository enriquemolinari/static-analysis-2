package ar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ar.api.PromoService;
import ar.api.Promos;
import ar.api.Promotion;
import ar.model.DateFormatted;
import ar.model.Discount;

public class DefaultDiscountService implements PromoService {

  private UnitOfWork work;

  public DefaultDiscountService(UnitOfWork tx) {
    this.work = tx;
  }

  @Override
  public Promos activePromosIn(String date) {

    List<Discount> l = work.tx(promos -> {
      return promos.discountsIn(new DateFormatted(date).value());
    });

    List<Promotion> ds =
        l.stream().map(d -> toPromocion(d)).collect(Collectors.toList());

    return new Promos() {
      @Override
      public Iterable<Promotion> promos() {
        return ds;
      }
    };
  }

  private Promotion toPromocion(Discount d) {
    return new Promotion() {

      @Override
      public Map<String, String> represent() {
        return d.toRepresent();
      }

      @Override
      public Long id() {
        return d.id();
      }

      @Override
      public String validTo() {
        return d.validoHasta();
      }

      @Override
      public String validFrom() {
        return d.validoDesde();
      }
    };
  }
}
