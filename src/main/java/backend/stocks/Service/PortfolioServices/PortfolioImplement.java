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

    public Double getTotalBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).get();

        List<Stocks> portfolioStocks = workingPortfolio.getStocksList();
        double totalBalance = 0.0;

        for (Stocks s : portfolioStocks) { // Geting all stocks' bid price, then + available balance on account
            totalBalance += s.getBid();
        }
        totalBalance += workingPortfolio.getAvailableBalance();
        workingPortfolio.setTotalBalance(totalBalance);

        portfolioRepo.save(workingPortfolio);

        return totalBalance;
    }

    public Double getReservedBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).get();

        List<Orders> portfolioOrders = workingPortfolio.getOrdersList();
        double reservedBalance = 0.0;

        for (Orders r : portfolioOrders) {
            if ("IN_PROGRESS".equals(r.getOrderStatus())) { // multiplying stock's quantity to its price
                Double quantity = r.getQuantity();
                Stocks stockPrice = r.getStockPrice();
                reservedBalance += quantity * stockPrice.getBid();
            }
        }
        workingPortfolio.setReservedBalance(reservedBalance);

        portfolioRepo.save(workingPortfolio);

        return reservedBalance;
    }
}
