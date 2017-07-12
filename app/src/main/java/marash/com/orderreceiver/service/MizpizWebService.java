package marash.com.orderreceiver.service;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Response;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import marash.com.orderreceiver.dto.OrdersByFPIdFromOrderIdDTO;
import marash.com.orderreceiver.request.MizpizRequest;
import marash.com.orderreceiver.request.RequestQueueProvider;

/**
 * Creates access to mizpiz web-services
 *
 * @author Arash Khosravi
 */

public class MizpizWebService {

    private static final String WEB_SERVICE_URL = "www.mizpizservices.com";
    private static final String ORDERS_BY_FOOD_PROVIDER_ID_FROM_ORDER_ID_URL = "OrdersByFoodProviderId";
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.US);

    static {
        dateFormatter.setTimeZone(TimeZone.getTimeZone("est"));
    }

    public static void getOrdersByFoodProviderIdFromOrderId(String foodProviderId,
                                                            long lastOrderId,
                                                            Response.Listener<OrdersByFPIdFromOrderIdDTO[]> listener,
                                                            Response.ErrorListener errorListener,
                                                            Context applicationContext) throws UnsupportedEncodingException {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(WEB_SERVICE_URL)
                .appendPath("MizPiz")
                .appendPath("BasicServices.svc")
                .appendPath("get")
                .appendPath(ORDERS_BY_FOOD_PROVIDER_ID_FROM_ORDER_ID_URL)
                .appendPath(foodProviderId)
                .appendPath(String.valueOf(lastOrderId));

        MizpizRequest<OrdersByFPIdFromOrderIdDTO[]> ordersRequest = new MizpizRequest<>(
                builder.build().toString(), OrdersByFPIdFromOrderIdDTO[].class, listener, errorListener);
        RequestQueueProvider.getInstance(applicationContext).addToRequestQueue(ordersRequest);
    }

}
