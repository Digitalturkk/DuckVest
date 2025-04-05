package backend.stocks.Models;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "Portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioId;
    @OneToOne
    private Investors investor;
    private int totalBalance;
    private int reservedBalance;
    private int availableBalance;
    private Instant lastUpdate = Instant.now();

    public int totalBalance(List<Stocks> Stocks) {
        this.totalBalance = 0;
        for (Stocks s : Stocks) {
            this.totalBalance += s.getBid();
        }
        this.totalBalance += this.availableBalance;
        return this.totalBalance;
    }

    public int reservedBalance(List<Orders> OrdersList) {
        this.reservedBalance = 0;
        for (Orders r : OrdersList) {
            if (r.getOrderStatus().equals("IN_PROGRESS")) {
                Double quantity = r.getQuantity();
                Stocks stockPrice = r.getStockPrice();
                this.reservedBalance += quantity * stockPrice.getBid();
            }
        }
        return this.reservedBalance;
    }


}
