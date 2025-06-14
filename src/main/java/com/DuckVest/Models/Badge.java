package com.DuckVest.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;
    @Column(name = "badge_name", unique = true)
    @NotBlank(message = "Badge name cannot be blank")
    private String badgeName;
    @Column(name = "badge_description")
    private String badgeDescription;
    //@Column(name = "badge_image") commented as we will use it in future for frontend
    //private String badgeImage;
    @Column(name = "badge_type")
    private String badgeType;
    @Column(name = "badge_criteria")
    private String badgeCriteria;

    @ManyToMany
    @JoinTable(
            name = "investor_badges",
            joinColumns = @JoinColumn(name = "badge_id"),
            inverseJoinColumns = @JoinColumn(name = "investor_id")
    )
    private Set<Investor> investor;

    public Badge() {
    }

    public Badge(Long badgeId, String badgeName, String badgeDescription, String badgeType, String badgeCriteria, Set<Investor> investor) {
        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.badgeDescription = badgeDescription;
        this.badgeType = badgeType;
        this.badgeCriteria = badgeCriteria;
        this.investor = investor;
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public @NotBlank(message = "Badge name cannot be blank") String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(@NotBlank(message = "Badge name cannot be blank") String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgeDescription() {
        return badgeDescription;
    }

    public void setBadgeDescription(String badgeDescription) {
        this.badgeDescription = badgeDescription;
    }

    public String getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(String badgeType) {
        this.badgeType = badgeType;
    }

    public String getBadgeCriteria() {
        return badgeCriteria;
    }

    public void setBadgeCriteria(String badgeCriteria) {
        this.badgeCriteria = badgeCriteria;
    }

    public Set<Investor> getInvestor() {
        return investor;
    }

    public void setInvestor(Set<Investor> investor) {
        this.investor = investor;
    }
}
