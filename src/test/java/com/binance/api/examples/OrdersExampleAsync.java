package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.request.*;

import java.util.List;

import static com.binance.api.client.domain.account.NewOrder.limitBuy;
import static com.binance.api.client.domain.account.NewOrder.marketBuy;

/**
 * Examples on how to place orders, cancel them, and query account information.
 */
public class OrdersExampleAsync {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

    // Getting list of open orders
    client.getOpenOrders(new OrderRequest("LINKETH"), new BinanceApiCallback<List<Order>>() {
        @Override
        public void onResponse(List<Order> response) {
            System.out.println(response);
        }

        @Override
        public void onFailure(Throwable cause) {
            System.out.println(cause);
        }
    });

    // Get status of a particular order
    client.getOrderStatus(new OrderStatusRequest("LINKETH", 745262L),
            new BinanceApiCallback<Order>() {
              @Override
              public void onResponse(Order response) {
                System.out.println(response);
              }

              @Override
              public void onFailure(Throwable cause) {
                  System.out.println(cause);
              }
            });

    // Getting list of all orders with a limit of 10
    client.getAllOrders(new AllOrdersRequest("LINKETH").limit(10), new BinanceApiCallback<List<Order>>() {
        @Override
        public void onResponse(List<Order> response) {
            System.out.println(response);
        }

        @Override
        public void onFailure(Throwable cause) {
            System.out.println(cause);
        }
    });

    // Canceling an order
    client.cancelOrder(new CancelOrderRequest("LINKETH", 756703L),
            new BinanceApiCallback<CancelOrderResponse>() {
                @Override
                public void onResponse(CancelOrderResponse response) {
                    System.out.println(response);
                }

                @Override
                public void onFailure(Throwable cause) {
                    System.out.println(cause);
                }
            });

    // Placing a test LIMIT order
    client.newOrderTest(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"),
            new BinanceApiCallback<Void>() {
                @Override
                public void onResponse(Void response) {
                    System.out.println("Test order has succeeded.");
                }

                @Override
                public void onFailure(Throwable cause) {
                    System.out.println(cause);
                }
            });

    // Placing a test MARKET order
    client.newOrderTest(marketBuy("LINKETH", "1000"), new BinanceApiCallback<Void>() {
        @Override
        public void onResponse(Void response) {
            System.out.println("Test order has succeeded.");
        }

        @Override
        public void onFailure(Throwable cause) {
            System.out.println(cause);
        }
    });

    // Placing a real LIMIT order
    client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"),
            new BinanceApiCallback<NewOrderResponse>() {
                @Override
                public void onResponse(NewOrderResponse response) {
                    System.out.println(response);
                }

                @Override
                public void onFailure(Throwable cause) {
                    System.out.println(cause);
                }
            });
  }
}
