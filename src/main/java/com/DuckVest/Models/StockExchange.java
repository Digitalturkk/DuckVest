package com.DuckVest.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "stock_exchange")
public class StockExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "Country cannot be blank")
    @Column(name = "country")
    private String country;
    @NotBlank(message = "City cannot be blank")
    @Column(name = "city")
    private String city;
    @NotBlank(message = "Currency cannot be blank")
    @Column(name = "currency")
    private String currency;
    @NotNull(message = "Active status cannot be null")
    private Boolean isActive;
    @NotNull(message = "Open time cannot be null")
    @Column(name = "open_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime openTime;
    @NotNull(message = "Close time cannot be null")
    @Column(name = "close_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime closeTime;

    @OneToMany(mappedBy = "stockExchange")
    private List<Stocks> stocks;

    public StockExchange() {}

    public StockExchange(Long id, String name, String city, String country, String currency,
                         Boolean isActive, ZonedDateTime openTime, ZonedDateTime closeTime) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.currency = currency;
        this.isActive = isActive;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public Boolean isActive() { return isActive; }
    public void setActive(Boolean active) { isActive = active; }

    public ZonedDateTime getOpenTime() { return openTime; }
    public void setOpenTime(ZonedDateTime openTime) { this.openTime = openTime; }

    public ZonedDateTime getCloseTime() { return closeTime; }
    public void setCloseTime(ZonedDateTime closeTime) { this.closeTime = closeTime; }

    public List<Stocks> getStocks() { return stocks; }
    public void setStocks(List<Stocks> stocks) { this.stocks = stocks; }
}



/* Old StockExchange.java file
package com.DuckVest.Models;

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
 */