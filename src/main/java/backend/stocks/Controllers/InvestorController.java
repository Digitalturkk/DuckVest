package backend.stocks.Controllers;

import backend.stocks.Models.Investors;
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
    public List<Investors> getAllInvestors() {
        return investorService.getAllInvestors();
    }

    @GetMapping("/get-id={id}")
    public Investors getInvestorById(@PathVariable Long id) {
        return investorService.getInvestor(id);
    }

    @PostMapping("/add")
    public void addInvestor(@RequestBody Investors investor) {
        investorService.saveInvestor(investor);
    }

    @DeleteMapping("/delete-id={id}")
    public void deleteInvestor(@PathVariable Long id) {
        investorService.deleteInvestor(id);
    }
}
