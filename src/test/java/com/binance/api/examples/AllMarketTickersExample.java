package com.binance.api.examples;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.AllMarketTickersEvent;

import java.util.List;

/**
 * All market tickers channel examples.
 *
 * It illustrates how to create a stream to obtain all market tickers.
 */
public class AllMarketTickersExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiWebSocketClient client = factory.newWebSocketClient();

    client.onAllMarketTickersEvent(new BinanceApiCallback<List<AllMarketTickersEvent>>() {
      @Override
      public void onResponse(List<AllMarketTickersEvent> event) {
        System.out.println(event);
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });
  }
}
