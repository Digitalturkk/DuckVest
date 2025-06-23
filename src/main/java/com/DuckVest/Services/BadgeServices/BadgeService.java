package com.DuckVest.Services.BadgeServices;

import com.DuckVest.DTOs.BadgeDTO;
import com.DuckVest.Models.Badge;

public interface BadgeService {
    Badge getBadgeById(Long badgeId);
    Badge updateBadge(Long badgeId, Badge badge);
    void saveBadge(Badge badge);
    void deleteBadge(Long badgeId);

    BadgeDTO createBadgeDTO(Long BadgeId);

    void checkBadgeBuyFirstStockCriteria(Long InvestorId);
    void checkBadgeOwnTeslaCriteria(Long InvestorId);
    void checkAllBadgeCriteria(Long InvestorId);
}
