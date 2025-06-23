package com.DuckVest.DTOs.BadgeDTOs;

import java.util.List;

// InvestorBadgesDTOs service is in com.DuckVest.Services.BadgeServices.BadgeService
public class InvestorBadgesDTO {
    private String username;
    private String name;
    private List<BadgeDTO> badges;

    public InvestorBadgesDTO(String username, String name, List<BadgeDTO> badges) {
        this.username = username;
        this.name = name;
        this.badges = badges;
    }

    public InvestorBadgesDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BadgeDTO> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeDTO> badges) {
        this.badges = badges;
    }
}
