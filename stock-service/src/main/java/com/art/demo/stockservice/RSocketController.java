package com.art.demo.stockservice;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class RSocketController
{
    final PriceService priceService;

    public RSocketController( PriceService priceService )
    {
        this.priceService = priceService;
    }

    @MessageMapping("stockPrices")
    public Flux<StockPrice> prices( String symbol )
    {
        return priceService.generatePrices( symbol );
    }
}
