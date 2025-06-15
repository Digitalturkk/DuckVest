package com.DuckVest.Services.BadgeServices;

import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.Badge;
import com.DuckVest.Repositories.BadgeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeImplement implements BadgeService {

    @Autowired
    private BadgeRepo badgeRepo;

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

}
