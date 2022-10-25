package ar.jpa;

import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import ar.model.SalesOnline;
import ar.service.UnitOfWork;

public class JpaUnitOfWork implements UnitOfWork {

  private EntityManagerFactory emf;

  public JpaUnitOfWork(String persistenceUnit) {
    this.emf = Persistence.createEntityManagerFactory(persistenceUnit);
  }

  @Override
  public <R> R tx(Function<SalesOnline, R> bloqueDeCodigo) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();

      R result = bloqueDeCodigo.apply(new JpaSalesOnline(em));

      tx.commit();
      return result;
    } catch (Exception e) {
      tx.rollback();
      throw new RuntimeException(e);
    } finally {
      em.close();
    }
  }
}
