package com.DuckVest.Services.Additional;

import com.DuckVest.Models.Stocks;
import com.DuckVest.Repositories.StocksRepo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class StockPriceUpdater {

    private static final String API_KEY = "f7b20df80cf14f4a9817676ff26d1e98"; // Замените на ваш API ключ, этот я украл с GitHub
    private static final String API_URL = "https://api.twelvedata.com/price?symbol=%s&apikey=" + API_KEY;

    @Autowired
    private StocksRepo stocksRepo;

    private final WebClient webClient = WebClient.create();

    @Scheduled(fixedRate = 3600000) // Обновлять каждый час
    public void updateAllPrices() {
        List<Stocks> allStocks = stocksRepo.findAll();

        for (Stocks stock : allStocks) {
            if (stock.getId() < 25) {
                String symbol = stock.getSymbol();
                String url = String.format(API_URL, symbol);

                webClient.get()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(JsonNode.class)
                        .subscribe(response -> {
                            try {
                                double newPrice = response.get("price").asDouble();
                                stock.setPrice(newPrice);
                                stock.setAsk(newPrice + 1);
                                stock.setBid(newPrice - 1);
                                stocksRepo.save(stock);
                                System.out.println("Updated: " + symbol + " to $" + newPrice);
                            } catch (Exception e) {
                                System.err.println("Error updating " + symbol + stock.getId());
                            }
                        });
            }

        }
    }

    public void updatePrice(Stocks stock) {
        String symbol = stock.getSymbol();
        String url = String.format(API_URL, symbol);

        webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .subscribe(response -> {
                    try {
                        double newPrice = response.get("price").asDouble();
                        stock.setPrice(newPrice);
                        stock.setAsk(newPrice + 1);
                        stock.setBid(newPrice - 1);
                        stocksRepo.save(stock);
                        System.out.println("Updated: " + symbol + " to $" + newPrice);
                    } catch (Exception e) {
                        System.err.println("Error updating " + symbol);
                    }
                });
    }
}