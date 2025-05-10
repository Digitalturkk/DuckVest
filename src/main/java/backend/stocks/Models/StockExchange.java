package backend.stocks.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_exchange")
public class StockExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;


    public StockExchange() {
    }

    public StockExchange(Long id, String name, String city, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
