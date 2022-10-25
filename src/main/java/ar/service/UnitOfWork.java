package ar.service;

import java.util.function.Function;
import ar.model.SalesOnline;

public interface UnitOfWork {

  <R> R tx(Function<SalesOnline, R> bloqueDeCodigo);
}
