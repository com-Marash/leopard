package marash.com.orderreceiver.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Provides a singleton instance of Volley {@link RequestQueue}
 *
 * Usage example:
 * Get a RequestQueue:
 * RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
 * getRequestQueue();
 * ...
 * Add a request (in this example, called stringRequest) to your RequestQueue.
 * MySingleton.getInstance(this).addToRequestQueue(stringRequest);
 *
 * @author Arash Khosravi
 */

public class RequestQueueProvider {
    private static RequestQueueProvider requestQueueProviderInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private RequestQueueProvider(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized RequestQueueProvider getInstance(Context context) {
        if (requestQueueProviderInstance == null) {
            requestQueueProviderInstance = new RequestQueueProvider(context);
        }
        return requestQueueProviderInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
