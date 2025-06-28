package com.DuckVest.DTOs.BadgeDTOs;

import com.DuckVest.CustomEnums.BadgeCriteria;

// BadgeDTO's service is in com.DuckVest.Services.BadgeService
public class BadgeDTO {
    private String badgeImageUrl;
    private String badgeName;
    private String badgeDescription;
    private BadgeCriteria badgeCriteria;

    public BadgeDTO() {
    }

    public BadgeDTO(String badgeName, String badgeDescription, BadgeCriteria badgeCriteria, String badgeImageUrl) {
        this.badgeName = badgeName;
        this.badgeDescription = badgeDescription;
        this.badgeCriteria = badgeCriteria;
        this.badgeImageUrl = badgeImageUrl;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgeDescription() {
        return badgeDescription;
    }

    public void setBadgeDescription(String badgeDescription) {
        this.badgeDescription = badgeDescription;
    }

    public BadgeCriteria getBadgeCriteria() {
        return badgeCriteria;
    }

    public void setBadgeCriteria(BadgeCriteria badgeCriteria) {
        this.badgeCriteria = badgeCriteria;
    }

    public String getBadgeImageUrl() {
        return badgeImageUrl;
    }

    public void setBadgeImageUrl(String badgeImageUrl) {
        this.badgeImageUrl = badgeImageUrl;
    }
}
