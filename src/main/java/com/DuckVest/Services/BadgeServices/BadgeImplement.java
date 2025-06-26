package com.DuckVest.Services.BadgeServices;

import com.DuckVest.DTOs.BadgeDTOs.BadgeDTO;
import com.DuckVest.DTOs.BadgeDTOs.InvestorBadgesDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.*;
import com.DuckVest.Repositories.BadgeRepo;
import com.DuckVest.Repositories.PortfolioStocksRepo;
import com.DuckVest.Services.InvestorServices.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.DuckVest.CustomEnums.BadgeCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BadgeImplement implements BadgeService {

    @Autowired
    private BadgeRepo badgeRepo;
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
    public BadgeDTO transformBadgeToBadgeDTO(Badge badge) {
        Long badgeId = badge.getBadgeId();
        return createBadgeDTO(badgeId);
    }

    @Override
    @Async
    public void checkBadgeBuyFirstStockCriteria(Long investorId) {
        Investor investor = investorService.getInvestorById(investorId);
        Portfolio portfolio = investor.getPortfolio();
        Badge badge = badgeRepo.findBadgeByBadgeCriteria(BadgeCriteria.BUY_FIRST_STOCK);

        if (!investor.getBadges().contains(badge) && !portfolio.getPortfolioStocks().isEmpty()) {
            investor.getBadges().add(badge); // связь ManyToMany
            investorService.saveInvestor(investor);
        }
    }

    @Override
    @Async
    public void checkBadgeOwnTeslaCriteria(Long investorId) {
        Investor investor = investorService.getInvestorById(investorId);
        Badge badge = badgeRepo.findBadgeByBadgeCriteria(BadgeCriteria.OWN_TESLA_STOCK);
        List<Badge> investorBadges = investor.getBadges();
        List<PortfolioStocks> portfolioStocks = portfolioStocksRepo.findAllByPortfolio(investor.getPortfolio());
        if (portfolioStocks.stream().anyMatch(portfolioStock -> portfolioStock.getStock().getCompanyName().equals("Tesla")) && !investorBadges.contains(badge)) {
            investorBadges.add(badge);
            investor.setBadges(investorBadges);
            investorService.saveInvestor(investor);
            System.out.println("Badge added: " + badge.getBadgeName());
        }
    }

    @Override
    @Async
    @Transactional
    public void checkAllBuyBadgeCriteria(Long investorId) {
        checkBadgeBuyFirstStockCriteria(investorId);
        checkBadgeOwnTeslaCriteria(investorId);
    }

    @Override
    public InvestorBadgesDTO createInvestorBadgesDTO(Long investorId) {
        Investor investor = investorService.getInvestorById(investorId);
        checkAllBuyBadgeCriteria(investorId);
        List <Badge> investorBadges = investor.getBadges();
        investorBadges.size(); // Ensure the badges are loaded
        List<BadgeDTO> badgesDTO = new ArrayList<>();
        for (Badge badge : investorBadges) {
            BadgeDTO transferedBadgeDTO = transformBadgeToBadgeDTO(badge);
            System.out.println("Transformed BadgeDTO: " + transferedBadgeDTO);
            badgesDTO.add(transferedBadgeDTO);
        }

        System.out.println("Badges in DTO: " + badgesDTO);
        return new InvestorBadgesDTO(
                investor.getUsername(),
                investor.getName(),
                badgesDTO);
    }

}
