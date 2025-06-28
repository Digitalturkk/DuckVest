package com.DuckVest.Services.BadgeServices;

import com.DuckVest.DTOs.BadgeDTOs.BadgeDTO;
import com.DuckVest.DTOs.BadgeDTOs.InvestorBadgesDTO;
import com.DuckVest.Models.Badge;

import java.util.List;

public interface BadgeService {
    Badge getBadgeById(Long badgeId);
    Badge updateBadge(Long badgeId, Badge badge);
    void saveBadge(Badge badge);
    void deleteBadge(Long badgeId);

    BadgeDTO createBadgeDTO(Long BadgeId);
    BadgeDTO transformBadgeToBadgeDTO(Badge badge);
    InvestorBadgesDTO createInvestorBadgesDTO(Long InvestorId);

    void checkBadgeBuyFirstStockCriteria(Long InvestorId);
    void checkBadgeOwnTeslaCriteria(Long InvestorId);
    void checkAllBuyBadgeCriteria(Long InvestorId);

    List<BadgeDTO> getAllBadges();
}
