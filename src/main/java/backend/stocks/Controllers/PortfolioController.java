package backend.stocks.Controllers;

import backend.stocks.Models.Portfolio;
import backend.stocks.Services.PortfolioServices.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/get-all")
    public List<Portfolio> getAllPortfolios() {
        return portfolioService.getAllPortfolios();
    }

    @GetMapping("/get-id={id}")
    public Portfolio getPortfolioById(Long id) {
        return portfolioService.getPortfolioById(id);
    }

    @GetMapping("/get-total-balance={id}")
    public Double getTotalBalance(Long id) {
        return portfolioService.getTotalBalance(id);
    }

    @GetMapping("/get-reserved-balance={id}")
    public Double getReservedBalance(Long id) {
        return portfolioService.getReservedBalance(id);
    }

    @GetMapping("/get-available-balance={id}")
    public Double getAvailableBalance(Long id) {
        return portfolioService.getTotalBalance(id) - portfolioService.getReservedBalance(id);
    }

    @GetMapping("/get-portfolio-value={id}")
    public Double getPortfolioValue(Long id) {
        return portfolioService.getTotalBalance(id) + portfolioService.getReservedBalance(id);
    }

    @PostMapping("/add")
    public void addPortfolio(Portfolio portfolio) {
        portfolioService.savePortfolio(portfolio);
    }

}
