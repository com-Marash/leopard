package marash.com.orderreceiver.request;

import android.util.Base64;

import com.android.volley.Response;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import marash.com.orderreceiver.helper.StringHelper;

/**
 * Base class for all Mizpiz requests
 *
 * @author Arash Khosravi
 */

public class MizpizRequest<T> extends GsonRequest<T> {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh^mm^ss");
    {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GTM"));
    }

    /**
     * Make a GET Mizpiz request and return a parsed object from JSON.
     */
    public MizpizRequest(String url, Class<T> clazz,
                         Response.Listener<T> listener, Response.ErrorListener errorListener) throws UnsupportedEncodingException {
        super(url, clazz, new HashMap<String, String>(), listener, errorListener);
        populateHeader();
    }

    /**
     * Make a GET Mizpiz request and return a parsed object from JSON without error handler.
     */
    public MizpizRequest(String url, Class<T> clazz,
                         Response.Listener<T> listener) throws UnsupportedEncodingException {
        super(url, clazz, new HashMap<String, String>(), listener, null);
        populateHeader();
    }

    private void populateHeader() throws UnsupportedEncodingException {
        String now = dateFormat.format(new Date());
        String user = "-1362#" + now;
        String pass = StringHelper.computeSha1OfString("AtMiz"+ user +"IranPiz");
        String code= user + ":" + pass;
        byte[] encodedCode = Base64.encode(code.getBytes("CP1252"), Base64.DEFAULT);
        String finalAuth = "Basic " +  new String(encodedCode, "CP1252").replaceAll("\\r\\n|\\r|\\n", "");
        this.headers.put("Authorization", finalAuth);
    }
}
