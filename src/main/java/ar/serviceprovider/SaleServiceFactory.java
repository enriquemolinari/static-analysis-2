package ar.serviceprovider;

import ar.api.SaleService;
import ar.jpa.JpaUnitOfWork;
import ar.service.AVendorProvider;
import ar.service.DefaultSaleService;
import ar.service.UnitOfWork;

public class SaleServiceFactory {

  private static UnitOfWork unitOfWork;

  public static SaleService provider() {

    if (unitOfWork == null)
      unitOfWork = new JpaUnitOfWork("prod-conn");

    return new DefaultSaleService(unitOfWork, new AVendorProvider());
  }
}
