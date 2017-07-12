package marash.com.orderreceiver.service;

import android.content.Context;
import android.os.Handler;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import marash.com.orderreceiver.Setting;
import marash.com.orderreceiver.dto.OrdersByFPIdFromOrderIdDTO;

/**
 * description here...
 *
 * @author Maedeh.
 */

public class OrderFetcherService {

    private static Response.Listener<OrdersByFPIdFromOrderIdDTO[]> listener = new Response.Listener<OrdersByFPIdFromOrderIdDTO[]>() {
        @Override
        public void onResponse(OrdersByFPIdFromOrderIdDTO[] orders) {
            System.out.println(orders.length);
        }
    };

    private static Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("dadsad");
        }
    };


    public static void startOrderFetcher(final Context applicationContext) {
        final Handler handler = new Handler();
        Runnable fetchOrdersRunnable = new Runnable() {
            @Override
            public void run() {
                try{
                    MizpizWebService.getOrdersByFoodProviderIdFromOrderId(
                            Setting.FOOD_PROVIDER_ID, Setting.LAST_ORDER_ID, listener, errorListener, applicationContext);
                }
                catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("hello");
                }
                finally{
                    handler.postDelayed(this, Setting.ORDER_FETCHER_INTERVAL);
                }
            }
        };
        handler.post(fetchOrdersRunnable);
    }
}
