package blockchainvideoapp.com.goviddo.goviddo.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import blockchainvideoapp.com.goviddo.goviddo.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, 5000);


    }
}
