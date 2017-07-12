package marash.com.orderreceiver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import marash.com.orderreceiver.service.OrderFetcherService;

public class MainActivity extends AppCompatActivity {

    private static boolean hasOrderFetcherStarted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // run order fetcher
        if (!hasOrderFetcherStarted) {
            hasOrderFetcherStarted = true;
            OrderFetcherService.startOrderFetcher(this.getApplicationContext());
        }

    }

}
