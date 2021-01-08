package com.art.demo.stockui;

import com.art.demo.stockclient.StockClient;
import com.art.demo.stockclient.StockPrice;
import com.art.demo.stockclient.WebClientStockClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ChartController
{
    @FXML
    public LineChart<String, Double> chart;
    private StockClient stockClient;


    public ChartController( StockClient stockClient )
    {
        this.stockClient = stockClient;
    }

    @FXML
    public void initialize()
    {
        String symbol1 = "SYMBOL1";
        final PriceSubscriber priceSubscriber1 = new PriceSubscriber( symbol1 );
        stockClient.pricesFor( symbol1 ).subscribe( priceSubscriber1 );

        String symbol2 = "SYMBOL2";
        final PriceSubscriber priceSubscriber2 = new PriceSubscriber( symbol2 );
        stockClient.pricesFor( symbol2 ).subscribe( priceSubscriber2 );


        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        data.add( priceSubscriber1.getSeries() );
        data.add( priceSubscriber2.getSeries() );
        chart.setData( data );

    }



    private static class PriceSubscriber implements Consumer<StockPrice>
    {
        final private ObservableList<XYChart.Data<String, Double>> seriesData = FXCollections.observableArrayList();
        final private XYChart.Series<String, Double> series;

        public PriceSubscriber( String symbol )
        {
            series = new XYChart.Series<>( symbol, seriesData);
        }

        @Override
        public void accept( StockPrice stockPrice )
        {
            Platform.runLater( () ->
                    seriesData.add(
                            new XYChart.Data<>(
                                    String.valueOf( stockPrice.getTime().getSecond()),
                                    stockPrice.getPrice() ) )
            );
        }

        public XYChart.Series<String, Double> getSeries()
        {
            return series;
        }
    }
}
