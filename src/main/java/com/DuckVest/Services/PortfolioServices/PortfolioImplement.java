package com.DuckVest.Services.PortfolioServices;

import com.DuckVest.CustomEnums.Currency;
import com.DuckVest.CustomEnums.OrderStatus;
import com.DuckVest.CustomEnums.TransactionStatus;
import com.DuckVest.CustomEnums.TransactionType;
import com.DuckVest.DTOs.BadgeDTOs.InvestorBadgesDTO;
import com.DuckVest.DTOs.BankMoneyTransactionDTO;
import com.DuckVest.DTOs.PortfolioDTO;
import com.DuckVest.DTOs.PortfolioStocksDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.*;
import com.DuckVest.Repositories.InvestorsRepo;
import com.DuckVest.Repositories.PortfolioRepo;
import com.DuckVest.Repositories.PortfolioStocksRepo;
import com.DuckVest.Services.BadgeServices.BadgeService;
import com.DuckVest.Services.BankTransactionServices.BankTransactionService;
import com.DuckVest.Services.Additional.JavaMailSenderServices.JMSService;
import com.DuckVest.Services.OrdersServices.OrderService;
import com.DuckVest.Services.PortfolioStocksServices.PortfolioStocksService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.DuckVest.Services.Additional.DuckVestorServices.DuckVestorTipsService.getRandomAdvice;

@Service
public class PortfolioImplement implements PortfolioService {

    @Autowired
    PortfolioRepo portfolioRepo;
    @Autowired
    BadgeService badgeService;
    @Autowired
    InvestorsRepo investorsRepo;
    @Autowired
    OrderService orderService;
    @Autowired
    PortfolioStocksRepo portfolioStocksRepo;
    @Autowired
    BankTransactionService bankTransactionService;
    @Autowired
    JMSService jmsService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    @Lazy
    PortfolioStocksService portfolioStocksService;

    @Override
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepo.findAll();
    }

    @Override
    public Portfolio getPortfolioById(Long id) {
        return portfolioRepo.findById(id).orElseThrow(() -> new GlobalNotFoundException("Portfolio not found with id: " + id, null));
    }

    @Override
    public void savePortfolio(Portfolio portfolio) {
        portfolioRepo.save(portfolio);
    }

    @Override
    public void updatePortfolio(Portfolio portfolio) {
        Portfolio updatingPortfolio = portfolioRepo.findById(portfolio.getPortfolioId()).orElseThrow(() -> new GlobalNotFoundException("Portfolio not found with id: " + portfolio.getPortfolioId(), null));
        modelMapper.map(updatingPortfolio, portfolio);
    }

    @Override
    public void updateTotalBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).orElseThrow(() -> new GlobalNotFoundException("Portfolio not found with id: " + id, null));
        List<PortfolioStocks> portfolioStocks = portfolioStocksRepo.findAllByPortfolio(workingPortfolio);

        double totalBalance = 0.0;
        totalBalance += workingPortfolio.getAvailableBalance();
        totalBalance += getReservedBalance(id);

        for (PortfolioStocks ps : portfolioStocks) {
            double stockPrice = ps.getStock().getPrice() * ps.getQuantity();
            totalBalance += stockPrice;
        }

        workingPortfolio.setTotalBalance(totalBalance);

        portfolioRepo.save(workingPortfolio);
    }

    @Override
    public Double getReservedBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).orElseThrow(() -> new GlobalNotFoundException("Portfolio not found with id: " + id, null));

        List<Orders> portfolioOrders = workingPortfolio.getOrdersList();
        double reservedBalance = 0.0;

        for (Orders r : portfolioOrders) { // Getting all stocks' bid price, then + available balance on account
            if (r.getOrderStatus() == OrderStatus.IN_PROGRESS) {
                Double quantity = r.getQuantity();
                Double stockPrice = r.getStockPrice();
                reservedBalance += quantity * stockPrice;
            }
        }

        workingPortfolio.setReservedBalance(reservedBalance);

        portfolioRepo.save(workingPortfolio);

        return reservedBalance;
    }

    @Override

    public BankMoneyTransactionDTO addMoneyToPortfolio(Long id, Double amount) {
        Portfolio portfolio = portfolioRepo.findById(id).orElseThrow(() -> new GlobalNotFoundException("Portfolio not found with id: " + id, null));
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to add must be greater than zero.");
        }
        portfolio.setAvailableBalance(portfolio.getAvailableBalance() + amount);
        portfolioRepo.save(portfolio);
        updateTotalBalance(id);

        BankTransaction bankTransaction = new BankTransaction(
                amount,
                TransactionType.DEPOSIT,
                TransactionStatus.COMPLETED,
                Currency.USD,
                0.0,
                amount + " USD deposited to your portfolio.\n" +
                        "Your available balance is now: " + portfolio.getAvailableBalance() + " USD.\n" +
                        "Use it wisely!\n" + getRandomAdvice(),
                null, // No fromPortfolio for deposit
                portfolio
        );
        bankTransactionService.saveBankTransaction(bankTransaction);
        jmsService.sendBankTransactionToMail(bankTransaction);

        return bankTransactionService.transferBankTransactionToBankMoneyTransactionDTO(bankTransaction);
    }

    @Override
    public PortfolioDTO createPortfolioDTO(Long portfolioId, Long investorId, Long portfolioStocksID) {
        Portfolio portfolio = portfolioRepo.findById(portfolioId).orElseThrow(() -> new GlobalNotFoundException("Portfolio not found with id: " + portfolioId, null));
        Investor investor = investorsRepo.findById(investorId).orElseThrow(() -> new GlobalNotFoundException("Investor not found with id: " + investorId, null));

        InvestorBadgesDTO badgesList = badgeService.createInvestorBadgesDTO(investorId);

        List<PortfolioStocks> portfolioStocksList = portfolioStocksRepo.findAllByPortfolio(portfolio);

        List<PortfolioStocksDTO> stocksList = new ArrayList<>();

        for (PortfolioStocks ps : portfolioStocksList) {
            PortfolioStocksDTO psDTO = portfolioStocksService.createPortfolioStocksDTO(ps);
            if (psDTO.getQuantity() <= 0) {
                portfolioStocksRepo.delete(ps);
                continue;
            }
            stocksList.add(psDTO);
        }

        PortfolioDTO portfolioDTO = new PortfolioDTO();
        portfolioDTO.setPortfolioId(portfolio.getPortfolioId());
        portfolioDTO.setInvestorName(investor.getName());
        portfolioDTO.setAvailableBalance(portfolio.getAvailableBalance());
        portfolioDTO.setReservedBalance(getReservedBalance(portfolioId));
        portfolioDTO.setTotalBalance(portfolio.getTotalBalance());
        portfolioDTO.setStocksList(stocksList);
        portfolioDTO.setOrdersList(
                portfolio.getOrdersList().stream()
                        .map(order -> orderService.createOrderDTO(order.getId(), investorId, order.getStock().getId()))
                        .toList()
        );
        portfolioDTO.setBadgesList(badgesList);
        return portfolioDTO;
    }

}
