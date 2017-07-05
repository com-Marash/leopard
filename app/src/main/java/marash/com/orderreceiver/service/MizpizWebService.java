package marash.com.orderreceiver.service;

import android.content.Context;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Creates access to mizpiz web-services
 *
 * @author Arash Khosravi
 */

public class MizpizWebService {

    private static final String WEB_SERVICE_URL = "www.mizpizservices.com/MizPiz/BasicServices.svc";
    private static final String ORDERS_BY_FOOD_PROVIDER_ID_FROM_ORDER_ID_URL = "OrdersByFoodProviderId";
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-ddThh:mm:ss", Locale.US);

    static {
        dateFormatter.setTimeZone(TimeZone.getTimeZone("est"));
    }

    public static void getOrdersByFoodProviderIdFromOrderId(String foodProviderId, long lastOrderId, Context applicationContext) {
            Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(WEB_SERVICE_URL)
                .appendPath("get")
                .appendPath(ORDERS_BY_FOOD_PROVIDER_ID_FROM_ORDER_ID_URL)
                .appendPath(foodProviderId)
                .appendPath(String.valueOf(lastOrderId));

        String myUrl = builder.build().toString();
    }

}
