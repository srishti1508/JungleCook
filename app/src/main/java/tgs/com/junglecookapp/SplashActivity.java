package tgs.com.junglecookapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    WebView webView;
    ImageView imgv1;
    LinearLayout constraintLayout;
    AnimationDrawable animationDrawable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        webView = findViewById(R.id.webView);
        imgv1 = findViewById(R.id.imgv1);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        imgv1.startAnimation(pulse);
        constraintLayout = (LinearLayout) findViewById(R.id.MainRootLayout);

        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        animationDrawable.setEnterFadeDuration(500);

        animationDrawable.setExitFadeDuration(4500);

        animationDrawable.start();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/two.gif");
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        checkedLogged();
    }

    private void checkedLogged() {
        new Thread()
        {
            public void run()
            {
                try {
                    Thread.sleep(SPLASH_TIME_OUT);
                    startActivity(new Intent(SplashActivity.this,Login.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
