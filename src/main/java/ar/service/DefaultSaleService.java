package ar.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import ar.api.ClientCart;
import ar.api.PurchaseDetail;
import ar.api.Sale;
import ar.api.SaleService;
import ar.api.Sales;
import ar.model.Cart;
import ar.model.OnlinePayment;
import ar.model.PaymentProvider;
import ar.model.SalesOnline;
import ar.model.Ticket;

public class DefaultSaleService implements SaleService {

  private UnitOfWork work;
  private PaymentProvider proveedorDePago;

  public DefaultSaleService(UnitOfWork tx, PaymentProvider proveedor) {
    this.work = tx;
    this.proveedorDePago = proveedor;
  }

  @Override
  public Sale confirm(Long clientId, Long cardId, int secCode,
      List<Long> idProducts, Long idDiscount) {
    Function<SalesOnline, Ticket> codeBlock = (salesOnline) -> {
      var client = salesOnline.client(clientId).orElseThrow(
          () -> new IllegalArgumentException("clientId does not exists..."));

      var card = client.creditCard(cardId).orElseThrow(
          () -> new IllegalArgumentException("cardId does not exists"));

      if (idDiscount == null) {
        return new OnlinePayment(this.proveedorDePago,
            new Cart(salesOnline.products(idProducts)), client).confirm(card,
                secCode);
      }
      return new OnlinePayment(this.proveedorDePago,
          new Cart(salesOnline.products(idProducts),
              salesOnline.discount(idDiscount)),
          client).confirm(card, secCode);
    };

    return toSale(work.tx(codeBlock));
  }

  @Override
  public Sale confirm(Long clientId, Long cardId, int secCode,
      List<Long> idProducts) {
    return this.confirm(clientId, cardId, secCode, idProducts, null);
  }

  @Override
  public ClientCart clientCart(Long clientId, List<Long> idProducts) {
    return this.clientCart(clientId, idProducts, null);
  }

  @Override
  public ClientCart clientCart(Long clientId, List<Long> idProducts,
      Long idDiscount) {
    Function<SalesOnline, Ticket> codeBlock = (salesOnline) -> {

      var client = salesOnline.client(clientId).orElseThrow(
          () -> new IllegalArgumentException("clientId does not exists..."));

      if (idDiscount == null)
        return new Cart(salesOnline.products(idProducts)).emitTicket(client);

      return new Cart(salesOnline.products(idProducts),
          salesOnline.discount(idDiscount)).emitTicket(client);

    };

    var ticket = work.tx(codeBlock);

    var purchaseDetail = this.toPurchaseDetail(ticket);

    return new ClientCart() {
      @Override
      public PurchaseDetail detail() {
        return purchaseDetail;
      }

      @Override
      public int clientPoints() {
        return ticket.clientPointsAfterDiscount() + ticket.earnedPoints();
      }
    };
  }

  private PurchaseDetail toPurchaseDetail(Ticket ticket) {
    return new PurchaseDetail() {
      @Override
      public double totalWithoutDiscount() {
        return ticket.amountWithoutDiscount();
      }

      @Override
      public int pointsToWin() {
        return ticket.earnedPoints();
      }

      @Override
      public double total() {
        return ticket.total();
      }

      @Override
      public List<Map<String, String>> items() {
        return ticket.items();
      }

      @Override
      public String date() {
        return ticket.date();
      }
    };
  }

  @Override
  public Sales salesBetween(String from, String to) {

    Function<SalesOnline, List<Ticket>> codeBlock = (salesOnline) -> {
      return salesOnline.salesBetween(from, to);
    };

    return toSales(codeBlock);
  }

  private Sales toSales(Function<SalesOnline, List<Ticket>> codeBlock) {
    List<Sale> sales = work.tx(codeBlock).stream().map(t -> this.toSale(t))
        .collect(Collectors.toList());

    return new Sales() {
      @Override
      public Iterable<Sale> sales() {
        return sales;
      }

      @Override
      public Double total() {
        return sales.stream().mapToDouble(v -> v.detail().total()).sum();
      }
    };
  }

  @Override
  public Sales purchasesBy(Long clientId) {
    Function<SalesOnline, List<Ticket>> codeBlock = (salesOnline) -> {
      return salesOnline.purchasesFrom(clientId);
    };

    return toSales(codeBlock);
  }

  private Sale toSale(Ticket ticket) {
    PurchaseDetail detalle = this.toPurchaseDetail(ticket);

    return new Sale() {
      @Override
      public Long id() {
        return ticket.id();
      }

      @Override
      public PurchaseDetail detail() {
        return detalle;
      }
    };
  }
}
