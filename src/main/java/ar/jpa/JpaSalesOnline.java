package ar.jpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import ar.model.Client;
import ar.model.DateFormatted;
import ar.model.Discount;
import ar.model.Product;
import ar.model.SalesOnline;
import ar.model.Ticket;

public class JpaSalesOnline implements SalesOnline {

  private EntityManager em;

  @SuppressFBWarnings(value = {"EI_EXPOSE_REP2"})
  public JpaSalesOnline(EntityManager em) {
    this.em = em;
  }

  @Override
  public List<Discount> discountsIn(LocalDateTime date) {
    return em.createQuery(
        "from Discount d where :date between d.discountInDate.validFrom and d.discountInDate.validTo",
        Discount.class).setParameter("date", date).getResultList();
  }

  @Override
  public Optional<Client> client(Long id) {
    return Optional.of(em.find(Client.class, id));
  }

  @Override
  public List<Product> products(List<Long> ids) {
    return em.createQuery("from Product p where p.id in (:ids)", Product.class)
        .setParameter("ids", ids).getResultList();
  }

  @Override
  public Discount discount(Long id) {
    return em.getReference(Discount.class, id);
  }

  @Override
  public List<Ticket> salesBetween(String from, String to) {
    return em.createQuery(
        "select distinct t from Ticket t join fetch t.items where t.salesDate "
            + "between :from and :to",
        Ticket.class).setParameter("from", new DateFormatted(from).value())
        .setParameter("to", new DateFormatted(to).value()).getResultList();
  }

  @Override
  public List<Ticket> purchasesFrom(Long idClient) {
    return em.createQuery(
        "select distinct t from Ticket t join fetch t.items where t.client.id = :idClient",
        Ticket.class).setParameter("idClient", idClient).getResultList();
  }
}
