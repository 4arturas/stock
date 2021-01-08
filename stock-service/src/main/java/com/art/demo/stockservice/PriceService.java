package com.art.demo.stockservice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PriceService
{
    public Flux<StockPrice> generatePrices( String symbol )
    {
        return Flux.interval( Duration.ofSeconds( 1 ) )
                .map(
                        num -> new StockPrice( symbol, randomStockPrice(), LocalDateTime.now() )
                );
    }

    private Double randomStockPrice()
    {
        Double price = ThreadLocalRandom.current().nextDouble( 100);
        return price;
    }
}
