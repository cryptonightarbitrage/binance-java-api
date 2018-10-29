package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.market.*;
import com.binance.api.client.exception.BinanceApiException;

import java.util.List;

/**
 * Examples on how to get market data information such as the latest price of a symbol, etc., in an async way.
 */
public class MarketDataEndpointsExampleAsync {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

    // Getting depth of a symbol (async)
    client.getOrderBook("NEOETH", 10, new BinanceApiCallback<OrderBook>() {
      @Override
      public void onResponse(OrderBook response) {
        System.out.println(response.getBids());
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });

    // Getting latest price of a symbol (async)
    client.get24HrPriceStatistics("NEOETH", new BinanceApiCallback<TickerStatistics>() {
      @Override
      public void onResponse(TickerStatistics response) {
        System.out.println(response);
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });

    // Getting all latest prices (async)
    client.getAllPrices((new BinanceApiCallback<List<TickerPrice>>() {
      @Override
      public void onResponse(List<TickerPrice> response) {
        System.out.println(response);
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    }));

    // Getting agg trades (async)
    client.getAggTrades("NEOETH", new BinanceApiCallback<List<AggTrade>>() {
      @Override
      public void onResponse(List<AggTrade> response) {
        System.out.println(response);
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });

    // Weekly candlestick bars for a symbol
    client.getCandlestickBars("NEOETH", CandlestickInterval.WEEKLY,
            new BinanceApiCallback<List<Candlestick>>() {
              @Override
              public void onResponse(List<Candlestick> response) {
                System.out.println(response);
              }

              @Override
              public void onFailure(Throwable cause) {
                System.out.println(cause);
              }
            });

    // Book tickers (async)
    client.getBookTickers(new BinanceApiCallback<List<BookTicker>>() {
      @Override
      public void onResponse(List<BookTicker> response) {
        System.out.println(response);
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });

    // Exception handling
    try {
      client.getOrderBook("UNKNOWN", 10, new BinanceApiCallback<OrderBook>() {
        @Override
        public void onResponse(OrderBook response) {
          System.out.println(response);
        }

        @Override
        public void onFailure(Throwable cause) {
          System.out.println(cause);
        }
      });
    } catch (BinanceApiException e) {
      System.out.println(e.getError().getCode()); // -1121
      System.out.println(e.getError().getMsg());  // Invalid symbol
    }
  }
}
