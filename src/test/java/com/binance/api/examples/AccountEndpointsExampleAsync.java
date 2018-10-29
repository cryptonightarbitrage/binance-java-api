package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.account.*;

import java.util.List;

/**
 * Examples on how to get account information.
 */
public class AccountEndpointsExampleAsync {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

    // Get account balances (async)
    client.getAccount(new BinanceApiCallback<Account>() {
        @Override
        public void onResponse(Account response) {
            System.out.println(response.getAssetBalance("ETH"));
        }

        @Override
        public void onFailure(Throwable cause) {
            System.out.println(cause);
        }
    });


    // Get list of trades (async)
    client.getMyTrades("NEOETH", new BinanceApiCallback<List<Trade>>() {
        @Override
        public void onResponse(List<Trade> response) {
            System.out.println(response);
        }

        @Override
        public void onFailure(Throwable cause) {
            System.out.println(cause);
        }
    });

    // Get withdraw history (async)
    client.getWithdrawHistory("ETH", new BinanceApiCallback<WithdrawHistory>() {
        @Override
        public void onResponse(WithdrawHistory response) {
            System.out.println(response);
        }

        @Override
        public void onFailure(Throwable cause) {
            System.out.println(cause);
        }
    });

    // Get deposit history (async)
    client.getDepositHistory("ETH", new BinanceApiCallback<DepositHistory>() {
        @Override
        public void onResponse(DepositHistory response) {
            System.out.println(response);
        }

        @Override
        public void onFailure(Throwable cause) {
            System.out.println(cause);
        }
    });

    // Withdraw (async)
    client.withdraw("ETH", "0x123", "0.1", null, null, new BinanceApiCallback<WithdrawResult>() {
        @Override
        public void onResponse(WithdrawResult response) {}

        @Override
        public void onFailure(Throwable cause) {
            System.out.println(cause);
        }
    });
  }
}
