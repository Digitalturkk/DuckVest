package backend.stocks.Service.PortfolioServices;

import backend.stocks.Models.Orders;
import backend.stocks.Models.Portfolio;
import backend.stocks.Models.Stocks;
import backend.stocks.Repositories.PortfolioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioImplement implements PortfolioService {

    @Autowired
    PortfolioRepo portfolioRepo;

    public List<Portfolio> getAllPortfolios() {
        return portfolioRepo.findAll();
    }

    public Portfolio getPortfolioById(Long id) {
        return portfolioRepo.findById(id).get();
    }

    public void savePortfolio(Portfolio portfolio) {
        portfolioRepo.save(portfolio);
    }

    public int getTotalBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).get();
        workingPortfolio.setTotalBalance(0);

        List<Stocks> portfolioStocks = workingPortfolio.getStocksList();
        int totalBalance = 0;

        for (Stocks s : portfolioStocks) { // Geting all stocks' bid price, then + available balance on account
            totalBalance += s.getBid();
        }
        totalBalance += workingPortfolio.getAvailableBalance();
        workingPortfolio.setTotalBalance(totalBalance);
        return totalBalance;
    }

    public int getReservedBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).get();
        workingPortfolio.setReservedBalance(0);

        List<Orders> portfolioOrders = workingPortfolio.getOrdersList();
        int reservedBalance = 0;

        for (Orders r : portfolioOrders) {
            if (r.getOrderStatus().equals("IN_PROGRESS")) { // multiplying stock's quantity to its price
                Double quantity = r.getQuantity();
                Stocks stockPrice = r.getStockPrice();
                reservedBalance += quantity * stockPrice.getBid();
            }
        }
        workingPortfolio.setReservedBalance(reservedBalance);
        return reservedBalance;
    }
}
