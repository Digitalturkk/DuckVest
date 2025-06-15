package com.DuckVest.Services.BadgeServices;

import com.DuckVest.Models.Badge;

public interface BadgeService {
    Badge getBadgeById(Long badgeId);
    Badge updateBadge(Long badgeId, Badge badge);
    void saveBadge(Badge badge);
    void deleteBadge(Long badgeId);
}
