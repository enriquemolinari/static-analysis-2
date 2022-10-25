package ar.serviceprovider;

import ar.api.PromoService;
import ar.jpa.JpaUnitOfWork;
import ar.service.DefaultDiscountService;
import ar.service.UnitOfWork;

public class DiscountServiceFactory {

  private static UnitOfWork unitOfWork;

  public static PromoService provider() {

    if (unitOfWork == null)
      unitOfWork = new JpaUnitOfWork("prod-conn");

    return new DefaultDiscountService(unitOfWork);
  }
}
