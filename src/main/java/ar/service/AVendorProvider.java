package ar.service;

import ar.model.PaymentProvider;

public class AVendorProvider implements PaymentProvider {

  @Override
  public void execute(double monto, String nro, int pin) {
    // your favourite implementation here...
  }

}
