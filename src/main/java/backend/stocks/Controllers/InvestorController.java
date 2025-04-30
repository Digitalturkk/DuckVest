package backend.stocks.Controllers;

import backend.stocks.Models.Investor;
import backend.stocks.Service.InvestorServices.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investors")
public class InvestorController {

    @Autowired
    InvestorService investorService;

    @GetMapping("/get-all")
    public List<Investor> getAllInvestors() {
        return investorService.getAllInvestors();
    }

    @GetMapping("/get-id={id}")
    public Investor getInvestorById(@PathVariable Long id) {
        return investorService.getInvestor(id);
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
