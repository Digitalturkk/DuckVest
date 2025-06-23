package com.DuckVest.Services.BadgeServices;

import com.DuckVest.DTOs.BadgeDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.*;
import com.DuckVest.Repositories.BadgeRepo;
import com.DuckVest.Repositories.PortfolioStocksRepo;
import com.DuckVest.Repositories.StocksRepo;
import com.DuckVest.Services.InvestorServices.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.DuckVest.CustomEnums.BadgeCriteria;

import java.util.List;

@Service
public class BadgeImplement implements BadgeService {

    @Autowired
    private BadgeRepo badgeRepo;
    @Autowired
    private StocksRepo stocksRepo;
    @Autowired
    private InvestorService investorService;
    @Autowired
    private PortfolioStocksRepo portfolioStocksRepo;


    @Override
    public Badge getBadgeById(Long badgeId) {
        return badgeRepo.findById(badgeId).orElseThrow(() -> new GlobalNotFoundException("Badge not found with id: " + badgeId, null));
    }

    @Override
    public Badge updateBadge(Long badgeId, Badge badge) {
        Badge existingBadge = getBadgeById(badgeId);
        existingBadge.setBadgeName(badge.getBadgeName());
        existingBadge.setBadgeDescription(badge.getBadgeDescription());
        existingBadge.setBadgeType(badge.getBadgeType());
        existingBadge.setBadgeCriteria(badge.getBadgeCriteria());
        existingBadge.setInvestor(badge.getInvestor());
        return badgeRepo.save(existingBadge);
    }

    @Override
    public void saveBadge(Badge badge) {
        badgeRepo.save(badge);
    }

    @Override
    public void deleteBadge(Long badgeId) {
        if (!badgeRepo.existsById(badgeId)) {
            throw new GlobalNotFoundException("Badge not found with id: " + badgeId, null);
        }
        badgeRepo.deleteById(badgeId);
    }

    @Override
    public BadgeDTO createBadgeDTO(Long badgeId) {
        Badge badge = getBadgeById(badgeId);
        return new BadgeDTO(
                badge.getBadgeName(),
                badge.getBadgeDescription(),
                badge.getBadgeCriteria()
        );
    }

    @Override
    public void checkBadgeBuyFirstStockCriteria(Long investorId) {
        Investor investor = investorService.getInvestorById(investorId);
        List<Badge> investorBadges = investor.getBadges();
        List<PortfolioStocks> portfolioStocks = portfolioStocksRepo.findAllByPortfolio(investor.getPortfolios().getFirst());
        if (!portfolioStocks.isEmpty()) {
            investorBadges.add(badgeRepo.findBadgeByBadgeCriteria(BadgeCriteria.BUY_FIRST_STOCK));
            investor.setBadges(investorBadges);
            investorService.saveInvestor(investor);
        }
    }

    @Override
    public void checkBadgeOwnTeslaCriteria(Long investorId) {
        Investor investor = investorService.getInvestorById(investorId);
        List<Badge> investorBadges = investor.getBadges();
        List<PortfolioStocks> portfolioStocks = portfolioStocksRepo.findAllByPortfolio(investor.getPortfolios().getFirst());
        if (portfolioStocks.stream().anyMatch(portfolioStock -> portfolioStock.getStock().getCompanyName().equals("Tesla"))) {
            investorBadges.add(badgeRepo.findBadgeByBadgeCriteria(BadgeCriteria.OWN_TESLA_STOCK));
            investor.setBadges(investorBadges);
            investorService.saveInvestor(investor);
        }
    }

    @Override
    public void checkAllBadgeCriteria(Long investorId) {
        checkBadgeBuyFirstStockCriteria(investorId);
        checkBadgeOwnTeslaCriteria(investorId);
    }

}
