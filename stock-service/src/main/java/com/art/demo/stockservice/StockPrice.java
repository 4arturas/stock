package com.art.demo.stockservice;

import java.time.LocalDateTime;

public class StockPrice
{
    private String symbol;
    private Double price;
    private LocalDateTime time;

    public StockPrice( String symbol, Double price, LocalDateTime time )
    {
        this.symbol = symbol;
        this.price = price;
        this.time = time;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public Double getPrice()
    {
        return price;
    }

    public LocalDateTime getTime()
    {
        return time;
    }
}
