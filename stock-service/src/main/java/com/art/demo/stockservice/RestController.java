package com.art.demo.stockservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@org.springframework.web.bind.annotation.RestController
public class RestController
{
    @GetMapping( value = "/stocks/{symbol}", produces = { MediaType.TEXT_EVENT_STREAM_VALUE } )
    public Flux<StockPrice> prices( @PathVariable String symbol )
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
