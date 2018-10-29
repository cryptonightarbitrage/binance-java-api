package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.general.*;

import java.util.List;

/**
 * Examples on how to use the general endpoints.
 */
public class GeneralEndpointsExampleAsync {

  public static void main(String[] args) throws InterruptedException {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

    // Test connectivity
    client.ping(new BinanceApiCallback<Void>() {
      @Override
      public void onResponse(Void response) {
        System.out.println("Ping succeeded.");
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });

    // Check server time
    client.getServerTime(new BinanceApiCallback<ServerTime>() {
      @Override
      public void onResponse(ServerTime response) {
        System.out.println(response.getServerTime());
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });

    // Exchange info
    client.getExchangeInfo(new BinanceApiCallback<ExchangeInfo>() {
      @Override
      public void onResponse(ExchangeInfo exchangeInfo) {
        System.out.println(exchangeInfo.getTimezone());
        System.out.println(exchangeInfo.getSymbols());

        // Obtain symbol information
        SymbolInfo symbolInfo = exchangeInfo.getSymbolInfo("ETHBTC");
        System.out.println(symbolInfo.getStatus());

        SymbolFilter priceFilter = symbolInfo.getSymbolFilter(FilterType.PRICE_FILTER);
        System.out.println(priceFilter.getMinPrice());
        System.out.println(priceFilter.getTickSize());
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });

    // Obtain asset information
    client.getAllAssets(new BinanceApiCallback<List<Asset>>() {
      @Override
      public void onResponse(List<Asset> allAssets) {
        System.out.println(allAssets.stream().filter(asset -> asset.getAssetCode().equals("BNB")).findFirst().get());
      }

      @Override
      public void onFailure(Throwable cause) {
        System.out.println(cause);
      }
    });
  }
}
