package com.art.demo.stockservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

@org.springframework.web.bind.annotation.RestController
public class RestController
{
    final private PriceService priceService;

    public RestController( PriceService priceService )
    {
        this.priceService = priceService;
    }

    @GetMapping( value = "/stocks/{symbol}", produces = { MediaType.TEXT_EVENT_STREAM_VALUE } )
    public Flux<StockPrice> prices( @PathVariable String symbol )
    {
        return priceService.generatePrices( symbol );
    }

}
