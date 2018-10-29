package com.binance.api.examples;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.market.CandlestickInterval;

import java.io.IOException;

/**
 * Market data stream endpoints examples.
 *
 * It illustrates how to create a stream to obtain updates on market data such as executed trades.
 */
public class MarketDataStreamExample {

  public static void main(String[] args) throws InterruptedException, IOException {
    BinanceApiWebSocketClient client = BinanceApiClientFactory.newInstance().newWebSocketClient();

    // Listen for aggregated trade events for ETH/BTC
    client.onAggTradeEvent("ethbtc", new BinanceApiCallback<AggTradeEvent>() {
      @Override
      public void onResponse(AggTradeEvent response) {
        System.out.println(response);
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });

    // Listen for changes in the order book in ETH/BTC
    client.onDepthEvent("ethbtc", new BinanceApiCallback<DepthEvent>() {
      @Override
      public void onResponse(DepthEvent response) {
        System.out.println(response);
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });

    // Obtain 1m candlesticks in real-time for ETH/BTC
    client.onCandlestickEvent("ethbtc", CandlestickInterval.ONE_MINUTE, new BinanceApiCallback<CandlestickEvent>() {
      @Override
      public void onResponse(CandlestickEvent response) {
        System.out.println(response);
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });
  }
}
