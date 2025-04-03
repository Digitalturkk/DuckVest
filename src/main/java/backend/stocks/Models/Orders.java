package backend.stocks.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Choose order type")
    private String orderType;
    @ManyToOne
    @NotNull
    private Investor investor;
    @OneToOne
    private Stocks stocks;

    // Work with portfolio
    // Add new attributes
}
