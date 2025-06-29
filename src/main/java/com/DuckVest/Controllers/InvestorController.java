package com.DuckVest.Controllers;

import com.DuckVest.DTOs.InvestorDTOs.InvestorAccountDTO;
import com.DuckVest.Models.Investor;
import com.DuckVest.Services.InvestorServices.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investors")
@CrossOrigin(origins = "*")
public class InvestorController {

    @Autowired
    InvestorService investorService;

    @PostMapping("/login-user={username}&pwd={password}") // returns the investor ID if credentials are valid
    public Long checkInvestorCredentials(@PathVariable String username, @PathVariable String password) {
        return investorService.checkInvestorCredentials(username, password);
    }

    @GetMapping("/get-account-information-id={investorId}")
    public InvestorAccountDTO getInvestorAccountInformation(@PathVariable Long investorId) {
        return investorService.createInvestorDTO(investorId);
    }

    @GetMapping("/get-all")
    public List<InvestorAccountDTO> getAllInvestors() {
        return investorService.createAllInvestorAccountDTOs();
    }

    @GetMapping("/get-id={id}")
    public Investor getInvestorById(@PathVariable Long id) {
        return investorService.getInvestorById(id);
    }

    @PostMapping("/add")
    public void addInvestor(@RequestBody Investor investor) {
        investorService.saveInvestor(investor);
    }

    @DeleteMapping("/delete-id={id}")
    public void deleteInvestor(@PathVariable Long id) {
        investorService.deleteInvestor(id);
    }

}
