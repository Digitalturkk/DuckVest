package com.DuckVest.Controllers;

import com.DuckVest.DTOs.BadgeDTOs.InvestorBadgesDTO;
import com.DuckVest.Services.BadgeServices.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/badges")
@CrossOrigin(origins = "*")
public class BadgeController {

    @Autowired
    BadgeService badgeService;

    @GetMapping("/get-all-investor-badges-id={investorId}")
    public InvestorBadgesDTO getAllInvestorBadges(@PathVariable Long investorId) {
        return badgeService.createInvestorBadgesDTO(investorId);
    }
}
