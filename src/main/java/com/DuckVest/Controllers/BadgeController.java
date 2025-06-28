package com.DuckVest.Controllers;

import com.DuckVest.DTOs.BadgeDTOs.BadgeDTO;
import com.DuckVest.DTOs.BadgeDTOs.InvestorBadgesDTO;
import com.DuckVest.Services.BadgeServices.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all")
    public List<BadgeDTO> getAllBadges() {
        return badgeService.getAllBadges();
    }
}
